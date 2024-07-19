package com.itheima;

import com.google.common.collect.Lists;
import com.itheima.common.DbUtils;
import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.itheima.common.DbUtils.synData;

public class DataMigrateProductToCk {



    public static void TruncateTable( String tableName) throws IOException {

        //目标数据源配置mybatis
        String TargetMapper = "mybatis_target_localCk_config.xml";
        SqlSession targetSession = DbUtils.getSqlSession(TargetMapper);
        String mapperNamePath = "TruncateMapper.truncate_"+tableName;

        try {
            // 写入数据前初始化数据表 truncate table 保留数据结构
            targetSession.update(mapperNamePath);  // TruncateMapper.truncateForbidden
            targetSession.commit(); // 提交事务
            System.out.println("TableName: " + tableName + " truncated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            targetSession.rollback(); // 回滚事务
            System.out.println("TableNae: " + tableName + " truncate failed.");
        }finally {
            // 关闭资源
            targetSession.close();
        }
    }



    /**
     * 数据迁移入ClickHouse
     * @param num 读取源数据条数
     * @throws IOException 异常
     */
    public static void DataMigratesInfCountryToCk(Integer num) throws IOException {
        // 数据源配置mybatis
        String SourceMapper = "mybatis_source_product_config.xml";
        SqlSession sourceSession = DbUtils.getSqlSession(SourceMapper);


        //目标数据源配置mybatis
        String TargetMapper = "mybatis_target_localCk_config.xml";
        SqlSession targetSession = DbUtils.getSqlSession(TargetMapper);


        // 读取源数据 调用函数 同步数据
        String sourceMapper = "com.example.ProductSourceMapper.selectInfoCountry";
        String targetMapper = "com.example.ProductTargetListMapper.saveInfCountry";
        synData(sourceSession, targetSession, sourceMapper, targetMapper, num);

    }
}
