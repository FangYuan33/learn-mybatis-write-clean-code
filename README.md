### 基础组件

#### PreparedStatement

`PreparedStatement` 是 JDBC 中一个强大而安全的接口，通过预编译 SQL 语句和使用占位符来设置参数，可以提高性能并防止 SQL 注入攻击。

##### 1. 预编译和缓存
`PreparedStatement` 对象代表一个预编译的 SQL 语句。预编译的好处是 SQL 语句只需要编译一次，而不需要每次执行时都重新编译。这对于频繁执行相同 SQL 语句的场景可以显著提高性能。

##### 2. 防止 SQL 注入
使用 `PreparedStatement` 可以有效防止 SQL 注入攻击。因为它使用占位符 (`?`) 来表示参数，而不是直接将用户输入拼接到 SQL 语句中，这样可以避免恶意用户通过特殊字符来篡改 SQL 语句。

##### 3. 设置参数
在 `PreparedStatement` 中，可以使用各种 `set` 方法来设置参数（如 `setInt`、`setString` 等）。这些方法会将参数值绑定到 SQL 语句中的对应占位符上。

##### 4. 执行 SQL 语句
`PreparedStatement` 可以执行各种类型的 SQL 语句，包括查询（`executeQuery`）、更新（`executeUpdate`）和任意 SQL 语句（`execute`）。

##### 示例代码
以下是一个使用 `PreparedStatement` 的简单示例：

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "username";
        String password = "password";
        
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 1 表示 set 的是第几个 ? 的参数
            pstmt.setString(1, "john_doe");
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

在这个示例中：

1. 建立数据库连接。
2. 创建 `PreparedStatement` 对象，并在 SQL 语句中使用占位符 (`?`)。
3. 使用 `setString` 方法设置参数值。
4. 执行查询并处理结果集。

#### Configuration

`org.apache.ibatis.session.Configuration` 是 MyBatis 框架中的一个核心类，用于管理和存储 MyBatis 的配置信息。该类包含了 MyBatis 运行时所需的各种配置参数和对象，如映射器（Mapper）、环境（Environment）、数据库连接池、事务管理器等。

下面是 `Configuration` 类的一些关键点：

1. **全局配置项**：包含 MyBatis 运行时的全局配置，比如懒加载、缓存、日志、别名、插件等。

2. **环境配置**：用于配置数据库连接信息和事务管理方式。通常在 MyBatis 配置文件中定义。

3. **映射器注册**：管理 MyBatis 的映射器（Mapper），即 SQL 映射文件和接口。`Configuration` 类会注册和维护这些映射器的信息。

4. **类型别名和处理器**：支持注册自定义的类型别名和类型处理器，简化映射配置。

5. **结果映射**：配置 SQL 查询结果与 Java 对象之间的映射关系。

6. **缓存配置**：支持一级缓存和二级缓存的配置，提升查询性能。

下面是一个简单的示例，展示如何使用 `Configuration` 类，通过读取 MyBatis 配置文件（`mybatis-config.xml`）来构建 `SqlSessionFactory`，然后获取 `Configuration` 对象，并输出了一些配置信息：

```java
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.Reader;

public class MyBatisExample {
    public static void main(String[] args) {
        try {
            // 读取 MyBatis 配置文件
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            // 构建 SqlSessionFactory
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            // 获取 Configuration 对象
            Configuration configuration = sqlSessionFactory.getConfiguration();

            // 输出一些配置信息
            System.out.println("Lazy Loading Enabled: " + configuration.isLazyLoadingEnabled());
            System.out.println("Aggressive Lazy Loading: " + configuration.isAggressiveLazyLoading());
            System.out.println("Default Statement Timeout: " + configuration.getDefaultStatementTimeout());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

#### MappedStatement

`org.apache.ibatis.mapping.MappedStatement`（映射SQL语句声明的类） 是 MyBatis 框架中的一个核心类，负责存储和管理映射 SQL 语句的详细信息。每个 `MappedStatement` 对象对应一个 `<select>`, `<insert>`, `<update>`, 或 `<delete>` 标签，这些标签定义在 MyBatis 的 XML 映射文件中。

以下是 `MappedStatement` 的主要功能和属性：

1. **ID**：每个 `MappedStatement` 都有一个唯一的 ID，用于在 MyBatis 配置中标识和引用该语句（就是Mapper接口中的方法声明）
2. **SQL 语句**：存储实际的 SQL 语句或动态 SQL 语句。
3. **参数映射**：描述输入参数的类型和映射关系。
4. **结果映射**：描述返回结果的类型和映射关系。
5. **SQL 类型**：指明 SQL 操作的类型，如 SELECT、INSERT、UPDATE 或 DELETE。
6. **缓存设置**：与二级缓存相关的设置。

以下是 `MappedStatement` 类的一些主要属性和方法的简要介绍：

- **属性**：
   - `id`：唯一标识符。
   - `sqlSource`：SQL 语句的源，可以是静态 SQL，也可以是动态 SQL。
   - `parameterMap`：参数映射信息。
   - `resultMaps`：结果映射信息。
   - `sqlCommandType`：SQL 命令类型（如 SELECT, INSERT, UPDATE, DELETE）。
   - `fetchSize`：用于设置 JDBC 的 fetch size。
   - `timeout`：SQL 语句的超时时间。
   - `statementType`：语句类型（如 STATEMENT, PREPARED, CALLABLE）。
   - `resultSetType`：结果集类型。
   - `keyGenerator`：主键生成策略。
   - `keyProperties`：主键属性。
   - `keyColumns`：主键列。
   - `databaseId`：数据库厂商标识，用于多数据库支持。

- **方法**：
   - `getId()`：获取 MappedStatement 的 ID。
   - `getSqlSource()`：获取 SQL 源。
   - `getParameterMap()`：获取参数映射信息。
   - `getResultMaps()`：获取结果映射信息。
   - `getSqlCommandType()`：获取 SQL 命令类型。
   - `getTimeout()`：获取超时时间。
   - `getFetchSize()`：获取 fetch size。
   - `getStatementType()`：获取语句类型。
   - `getResultSetType()`：获取结果集类型。
   - `getKeyGenerator()`：获取主键生成策略。
   - `getKeyProperties()`：获取主键属性。
   - `getKeyColumns()`：获取主键列。
   - `getDatabaseId()`：获取数据库厂商标识。

#### TypeHandler

1. 将Java类型的数据转换为数据库类型的数据。
2. 将数据库类型的数据转换为Java类型的数据。

MyBatis框架提供了一些默认的`TypeHandler`实现，例如处理字符串、整数、布尔值等基本数据类型的转换。同时，你也可以自定义`TypeHandler`来处理特殊的数据类型。

`TypeHandler`接口的核心方法有四个：
1. `void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;`
  - 这个方法用于将Java类型的数据设置到PreparedStatement中，以便执行SQL语句时传递参数。
2. `T getResult(ResultSet rs, String columnName) throws SQLException;`
  - 这个方法用于从ResultSet中根据列名获取数据，并将其转换为Java类型。
3. `T getResult(ResultSet rs, int columnIndex) throws SQLException;`
  - 这个方法用于从ResultSet中根据列索引获取数据，并将其转换为Java类型。
4. `T getResult(CallableStatement cs, int columnIndex) throws SQLException;`
  - 这个方法用于从CallableStatement中获取存储过程的输出参数，并将其转换为Java类型。

以下是一个简单的自定义`TypeHandler`示例，它将Java的`LocalDate`类型转换为SQL的`Date`类型：

##### 策略模式和模板方法模式

![design-pattern-TypeHandler.drawio.png](images%2Fdesign-pattern-TypeHandler.drawio.png)

如上图所示，`TypeHandler` 使用了模板方法和策略模式，根据不同的 JavaType 来实现不同的策略，由于其中部分逻辑是通用的，所以抽出了抽象层定义方法模板来实现代码的复用。

（可以将策略模式的意义理解成是对 `PreparedStatement` 和 `ResultSet` 公开出适配不同类型的方法的调用）

---

### 随处可见的 Configuration 


---

![img.png](images/framework.png)

### SQL 执行流程

1. 理解四个组件执行上的顺序

2. 每个组件的作用及意义

3. 掌握Execute 的三个实现逻辑

4. 掌握StatementHandler的三个实现逻辑

#### Execute 执行器


#### StatementHandler Sql处理器


#### ParameterHandler 参数处理器


#### ResultSetHandler 结果集映射处理器


### 映射配置

![img.png](images/ch02.png)

#### 加载解析

指用户配置的XML解析成JAVA配置对象。

比如 `<Select>....</Select>` 块,最终要解析成SqlSource。或 `<resultMap>...</resultMap>` 要解析成ResultMap对象。


#### 映射逻辑

比如SqlSource如何基于参数生成可执行SQL，ResultMap如何将结果集，解析成JAVA对象等


### 扩展支撑

![img.png](images/ch03.png)


---

### 巨人的肩膀

- [Mybatis 官方文档](https://mybatis.org/mybatis-3/zh_CN/index.html)
- [IDEA UML](https://www.jetbrains.com/help/idea/2024.1/class-diagram.html)
- [Github: How To Read Code](https://github.com/aredridel/how-to-read-code?tab=readme-ov-file) 
- [MyBatis源码阅读指南](https://www.bilibili.com/read/cv7933087)
- [《玩转 MyBatis：深度解析与定制》](https://s.juejin.cn/ds/YPqNJD8/)
