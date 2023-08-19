package interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 手动创建了一个拦截器，在下边儿选择需要拦截的类和方法，参数
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class UpdateInterceptor implements Interceptor {

    static class SqlSourceDecorator implements SqlSource {

        private SqlSource sqlSource;

        public SqlSourceDecorator(SqlSource sqlSource) {
            this.sqlSource = sqlSource;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
            // 看看是不是同一个对象
            System.out.println("看看看" + boundSql);

            return boundSql;
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("我们创建的拦截器生效了");
//        System.out.println(invocation.getTarget());
//        System.out.println(invocation.getMethod());
//        System.out.println(Arrays.toString(invocation.getArgs()));

        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        if (!(statement.getSqlSource() instanceof SqlSourceDecorator)) {
            Field sqlSource = statement.getClass().getDeclaredField("sqlSource");
            sqlSource.setAccessible(true);
            sqlSource.set(statement, new SqlSourceDecorator(statement.getSqlSource()));
        }


//        statement.getBoundSql(invocation.getArgs()[1]);

        return invocation.proceed();
    }
}
