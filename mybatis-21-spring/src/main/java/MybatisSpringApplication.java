import dao.DepartmentMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MybatisSpringApplication {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");

        DepartmentMapper departmentMapper = context.getBean(DepartmentMapper.class);
        System.out.println(departmentMapper.findAll());

        context.close();
    }
}
