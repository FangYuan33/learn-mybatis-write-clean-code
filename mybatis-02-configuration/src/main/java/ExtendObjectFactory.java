import entity.User;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

/**
 * 继承了DefaultObjectFactory，DefaultObjectFactory的作用是创建结果对象
 * 继承之后，我们有一个用户是没有年龄的，这样我们把它的年龄设定为33，可以通过以下方法
 * 若有数据的话，用户的年龄是不会变成33的
 *
 * @author fangyuan
 * @date 2021/11/30
 */
public class ExtendObjectFactory extends DefaultObjectFactory {

    /**
     * 这个方法它是先创建了一个字段全为null的对象，这里我们指定了年龄为33，
     * 那么数据库中该用户没有年龄的话，那么就有了默认值33，否则，33会被数据库中的值覆盖掉
     */
    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);

        // 这里是查询的对象如果没有年龄的话，那么指定它的年龄为33
        if (t instanceof User) {
            User user = (User) t;
            user.setAge(33);
        }

        return t;
    }
}
