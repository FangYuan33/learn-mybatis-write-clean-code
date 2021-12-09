package interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 效率查询插件 查看慢SQL
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}))
public class PerformanceInterceptor implements Interceptor {

    /**
     * 通过配置文件指定最大容忍超时时间
     */
    private long maxTolerate;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("慢Sql查询插件启动！");

        long startTime = System.currentTimeMillis();
        Object result = invocation.proceed();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;

        System.out.println("执行消耗时间" + time + "ms");
        if (time > maxTolerate) {
            Statement statement = (Statement) invocation.getArgs()[0];
            Field targetField = statement.getClass().getSuperclass().getDeclaredField("h");
            targetField.setAccessible(true);
            PreparedStatementLogger target = (PreparedStatementLogger) targetField.get(statement);
            PreparedStatement preparedStatement = target.getPreparedStatement();
            System.out.println("慢SQL：" + getSql(preparedStatement.toString()));
        }

        return result;
    }

    private String getSql(String statementStr) {
        Pattern pattern = Pattern.compile("(select | insert | update | delete).*");
        Matcher matcher = pattern.matcher(statementStr);
        if (matcher.find()) {
            return matcher.group();
        }
        return statementStr;
    }

    @Override
    public void setProperties(Properties properties) {
        this.maxTolerate = Long.parseLong(properties.getProperty("maxTolerate"));
    }
}
