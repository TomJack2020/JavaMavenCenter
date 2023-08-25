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

public class ProductSyn {

    /**
     * 产品系统部分数据同步
     * @param args  无参数
     * @throws IOException  异常抛出
     */
    public static void main(String[] args) throws IOException {

        // 1、加载资源 和获取SqlSession对象
        // 数据源配置mybatis---产品系统的数据库
        String SourceMapper = "mybatis_source_product_config.xml";
        InputStream inputStreamSource;
        inputStreamSource = Resources.getResourceAsStream(SourceMapper);
        SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
        SqlSession sourceSession = sourceSqlSessionFactory.openSession();



        //目标数据源配置mybatis ---本地系统数据库
        String TargetMapper = "mybatis_target_config.xml";
        InputStream inputStreamTarget;
        inputStreamTarget = Resources.getResourceAsStream(TargetMapper);
        SqlSessionFactory targetSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamTarget);
        SqlSession targetSession = targetSqlSessionFactory.openSession();


        // 2、执行sql
        // 写入数据前初始化数据表
        long t0 = System.currentTimeMillis();

//        SynProductInfo(sourceSession,targetSession); // 调用产品查询函数执行同步产品基础信息数据
        SynProductPicture(sourceSession,targetSession); // 调用产品查询函数执行同步产品图片数据

        //4、释放资源
        sourceSession.close();
        targetSession.close();

        long t1 = System.currentTimeMillis();
        System.out.printf("Use Time %s ms", (t1 - t0));
        //  1000条       fli 3367ms      li   886ms
        // 10000条       fli 24154ms     li
        // 100000条      fli 213738 ms   li   3414 ms
        // 1000000条     fli 213738 ms   li   26468 ms
    }

    /**
     * 同步产品系统===产品基础信息数据
     * @param sourceSession   数据源连接资源
     * @param targetSession   数据存储目标资源
     */
    public static void SynProductInfo(SqlSession sourceSession, SqlSession targetSession){

        try {
            targetSession.update("TruncateMapper.truncateProduct");
            targetSession.commit(); // 提交事务
            System.out.println("Table truncated Product successfully.");

            // 执行查询
            List<Product> dataList = sourceSession.selectList("com.example.SourceMapper.selectProduct");
            //数据分页存储--不然数据大了会爆内存
            List<List<Product>> listPartition = Lists.partition(dataList, 1000);  // 单词写入1000条
            for (List<Product> item : listPartition) {
                // 批量插入
                targetSession.insert("com.example.TargetListMapper.saveProduct", item);
                targetSession.commit(); // 提交事务
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 同步产品系统===产品图片信息数据
     * @param sourceSession   数据源连接资源
     * @param targetSession   数据存储目标资源
     */
    public static void SynProductPicture(SqlSession sourceSession, SqlSession targetSession){
        try {
            targetSession.update("TruncateMapper.truncateProductPicture");
            targetSession.commit(); // 提交事务
            System.out.println("Table truncated Product Picture successfully.");

            // 执行查询
            int limitNum = 10000;  //限制查询行数
            List<Product> dataList = sourceSession.selectList("com.example.SourceMapper.selectPicture", limitNum);
            //数据分页存储--不然数据大了会爆内存
            List<List<Product>> listPartition = Lists.partition(dataList, 1000);  // 单词写入1000条
            for (List<Product> item : listPartition) {
                // 批量插入
                targetSession.insert("com.example.TargetListMapper.savePicture", item);
                targetSession.commit(); // 提交事务
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
