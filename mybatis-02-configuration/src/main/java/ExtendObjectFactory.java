import entity.User;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

// 继承了DefaultObjectFactory，DefaultObjectFactory的作用是生成我们的Result结果
// 继承之后，我们有一个用户是没有年龄的，这样我们把它的年龄设定为33，可以通过以下方法
// 若有数据的话，用户的年龄是不会变成33的
public class ExtendObjectFactory extends DefaultObjectFactory {

    @Override
    public <T> T create(Class<T> type) {
        T t = super.create(type);
        if (t instanceof User) {
            User user = (User) t;
            user.setAge(33);
        }
        return t;
    }
}
