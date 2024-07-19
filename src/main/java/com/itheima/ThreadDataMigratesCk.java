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

public class ThreadDataMigratesCk {

    /**
     *  同步数据到目标数据库  【库表 from 产品系统 yibai_prod_base.yibai_prod_forbidden_grade】
     * @param num 批次号 0-9
     * @throws IOException error
     */
    public static void DataMigrates(Integer num) throws IOException {
        // 数据源配置mybatis
        String SourceMapper = "mybatis_source_product_config.xml";
        InputStream inputStreamSource;
        inputStreamSource = Resources.getResourceAsStream(SourceMapper);
        SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
        SqlSession sourceSession = sourceSqlSessionFactory.openSession();



        //目标数据源配置mybatis
        String TargetMapper = "mybatis_target_localCk_config.xml";
        InputStream inputStreamTarget;
        inputStreamTarget = Resources.getResourceAsStream(TargetMapper);
        SqlSessionFactory targetSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamTarget);
        SqlSession targetSession = targetSqlSessionFactory.openSession();

//        // 写入数据前初始化数据表 truncate table 保留数据结构
//        targetSession.update("TruncateMapper.truncateForbidden");
//        targetSession.commit(); // 提交事务
//        System.out.println("Table truncated successfully.");


        // 读取源数据
        //分批次读取 每次推移5W条数据

        int offset = num * 50000;
        List<Product> dataList = sourceSession.selectList("com.example.ProductSourceMapper.selectForbidden", offset);
            //System.out.printf("源数据条数 %s\n", dataList.size()); // 输出源数据条数  固定5W读取一次


        //数据分页存储--不然数据大了会爆内存
        List<List<Product>> listPartition = Lists.partition(dataList, 10000);
        for (List<Product> item : listPartition) {
            // 批量插入
            targetSession.insert("com.example.ProductTargetListMapper.saveForbidden", item);
            targetSession.commit(); // 提交事务
        }
        System.out.printf("数据写入 %s条成功\n", (num+1) * 50000);

        // 关闭资源
        sourceSession.close();
        targetSession.close();

    }



}
