
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
