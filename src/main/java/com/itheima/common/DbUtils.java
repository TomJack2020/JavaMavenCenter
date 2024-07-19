package com.itheima.common;

import com.google.common.collect.Lists;
import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class DbUtils {


    /**
     * 获取SqlSession对象
     * @param MapperPath  mapper文件路径
     * @return SqlSession对象
     */
    public static SqlSession getSqlSession(String MapperPath) {

        try {
            InputStream inputStreamSource;
            inputStreamSource = Resources.getResourceAsStream(MapperPath);
            SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
            SqlSession sourceSession;
            sourceSession = sourceSqlSessionFactory.openSession();
            return sourceSession;
        } catch (Exception e){
            System.out.println("获取SqlSession对象失败 \nReason:" + e.getMessage());;
        }
        //如果出现异常，则返回null
        return null;
    }


    /**
     *  同步数据
     * @param sourceSession 源数据库SqlSession对象
     * @param targetSession 目标数据库SqlSession对象
     * @param sourceMapper  源数据库mapper文件路径
     * @param targetMapper  目标数据库mapper文件路径
     * @param num          第几次同步，用于控制分页读取数据
     */
    public static void synData(SqlSession sourceSession, SqlSession targetSession, String sourceMapper, String targetMapper, int num) {

        try {// 读取源数据
            int offset = num * 50000;

            List<Product> dataList = sourceSession.selectList(sourceMapper, offset); // "com.example.ProductSourceMapper.selectForbidden"
            //System.out.printf("源数据条数 %s\n", dataList.size()); // 输出源数据条数  固定5W读取一次


            //数据分页存储--不然数据大了会爆内存
            List<List<Product>> listPartition = Lists.partition(dataList, 10000);
            for (List<Product> item : listPartition) {
                // 批量插入
                targetSession.insert(targetMapper, item);  // "com.example.ProductTargetListMapper.saveForbidden"
                targetSession.commit(); // 提交事务
            }
            System.out.printf("数据写入 %s条成功\n", (num + 1) * 50000);
            // 关闭源session
            sourceSession.close();
            targetSession.close();

        } catch (Exception e) {
            System.out.println("获取SqlSession对象失败 \nReason:" + e.getMessage());;
        }

    }



    public static void test_read(SqlSession sourceSession, SqlSession targetSession, String sourceMapper, String targetMapper, int num) {

        try {// 读取源数据
            int offset = num * 50;

            List<Product> dataList = sourceSession.selectList(sourceMapper, offset); // "com.example.ProductSourceMapper.selectForbidden"
            //System.out.printf("源数据条数 %s\n", dataList.size()); // 输出源数据条数  固定5W读取一次


            //数据分页存储--不然数据大了会爆内存
            List<List<Product>> listPartition = Lists.partition(dataList, 10);
            for (List<Product> item : listPartition) {
                System.out.println(item);
            }
            System.out.printf("数据写入 %s条成功\n", (num + 1) * 50);
            // 关闭源session
            sourceSession.close();
            targetSession.close();

        } catch (Exception e) {
            System.out.println("获取SqlSession对象失败 \nReason:" + e.getMessage());;
        }

    }



}
