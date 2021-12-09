package interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Arrays;

/**
 * 手动创建了一个拦截器，在下边儿选择需要拦截的类和方法，参数
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class UpdateInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("我们创建的拦截器生效了");
        System.out.println(invocation.getTarget());
        System.out.println(invocation.getMethod());
        System.out.println(Arrays.toString(invocation.getArgs()));

        return invocation.proceed();
    }
}
