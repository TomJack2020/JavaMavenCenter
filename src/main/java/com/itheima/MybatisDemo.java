package com.itheima;


import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        long t0 = System.currentTimeMillis();
        // 1、 加载mybatis的核心配置文件 获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、执行sql
//        List<User> user = sqlSession.selectList("test.selectAll");
        List<Product> user = sqlSession.selectList("test.selectAll");

        for (Product u : user) {
            System.out.println(u);
        }

        //4、释放资源
        sqlSession.close();

        long t1 = System.currentTimeMillis();
        System.out.printf("Use Time %s ms", (t1 - t0));


    }
}
