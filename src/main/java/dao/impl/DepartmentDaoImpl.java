package dao.impl;

import dao.DepartmentDao;
import entity.Department;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class DepartmentDaoImpl //implements DepartmentDao
{
    private SqlSessionFactory sqlSessionFactory;

    public DepartmentDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

//    @Override
    public List<Department> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectList("departmentMapper.findAll");
        }
    }

    //@Override
    public Department findById(String id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            return sqlSession.selectOne("departmentMapper.findById", id);
        }
    }
}
