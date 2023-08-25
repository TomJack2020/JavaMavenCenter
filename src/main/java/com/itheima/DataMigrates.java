package com.itheima;

import com.google.common.collect.Lists;
import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataMigrates {


    public static void main(String[] args) throws IOException {
        // 数据源配置mybatis
        String SourceMapper = "mybatis_source_product_config.xml";
        InputStream inputStreamSource;
        inputStreamSource = Resources.getResourceAsStream(SourceMapper);
        SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
        SqlSession sourceSession = sourceSqlSessionFactory.openSession();



        //目标数据源配置mybatis
        String TargetMapper = "mybatis_target_config.xml";
        InputStream inputStreamTarget;
        inputStreamTarget = Resources.getResourceAsStream(TargetMapper);
        SqlSessionFactory targetSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamTarget);
        SqlSession targetSession = targetSqlSessionFactory.openSession();

        // 写入数据前初始化数据表
        targetSession.update("TruncateMapper.truncateProduct");
        targetSession.commit(); // 提交事务
        System.out.println("Table truncated successfully.");
        long t0 = System.currentTimeMillis();

        List<Product> dataList = sourceSession.selectList("com.example.ProductSourceMapper.selectProduct");
        //数据分页存储--不然数据大了会爆内存
        List<List<Product>> listPartition = Lists.partition(dataList, 10000);
        int num = 0;
        for (List<Product> item : listPartition) {
            num += 1;
            System.out.printf("数据写入 %s条成功\n", num * 10000);
            // 批量插入
            targetSession.insert("com.example.ProductTargetListMapper.saveProduct", item);
            targetSession.commit(); // 提交事务
        }
//        System.out.println(dataList);

        // 单插入
//        for (Product record : dataList) {
//            targetSession.insert("com.example.TargetMapper.insertIntoTarget", record); // 硬写
//            targetSession.commit(); // 必须提交事务
//        }

        sourceSession.close();
        targetSession.close();

        long t1 = System.currentTimeMillis();
        System.out.printf("Use Time %s ms", (t1 - t0));
        //  1000条       fli 3367ms      li   886ms
        // 10000条       fli 24154ms     li
        // 100000条      fli 213738 ms   li   3414 ms
        // 1000000条     fli 213738 ms   li   26468 ms
    }



}
