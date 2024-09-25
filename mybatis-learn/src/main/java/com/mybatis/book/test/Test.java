package com.mybatis.book.test;

import com.mybatis.book.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        System.out.println(userMapper.findAll());

        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 获取SqlSession
     */
    public static SqlSession getSqlSession() throws IOException {
        InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // todo learn mybatis-config.xml parse
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(xml);

        return sqlSessionFactory.openSession();
    }
}
