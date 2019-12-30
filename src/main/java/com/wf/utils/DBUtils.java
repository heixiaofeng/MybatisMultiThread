package com.wf.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public final class DBUtils {
    public static SqlSessionFactory sessionFactory;

    static {
        InputStream resourceStream;
        try {
            resourceStream = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            sessionFactory = sqlSessionFactoryBuilder.build(resourceStream);
        } catch (IOException e) {
            e.printStackTrace();
//            System.out.println(e.getCause()+"......."+e.getMessage());
        }
    }

    public static SqlSession getSession() {
        return sessionFactory.openSession();
    }
}
