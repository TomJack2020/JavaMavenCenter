package com.itheima;

import com.itheima.pojo.CkProductData;
import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class CkSyn {

    public static void main(String[] args) throws IOException {
        // 数据源配置mybatis
        String SourceMapper = "mybatis_source_ck_config.xml";
        InputStream inputStreamSource;
        inputStreamSource = Resources.getResourceAsStream(SourceMapper);
        SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
        SqlSession sourceSession = sourceSqlSessionFactory.openSession();


        // 使用In模式读取数据 未使用map
        System.out.println("使用In模式读取数据 未使用map");
        selectProductIn(sourceSession);

        // 使用HashMap 匹配出对应的类型数据
        System.out.println("使用HashMap 匹配出对应的类型数据");
        selectProductMap(sourceSession);

        // 随机字段嵌入查询
        System.out.println("随机字段嵌入查询");
        String sku = "1019230041411";
        String column = "sku";


        Map<String, Object> params = new HashMap<>();
        params.put("column", column);
        params.put("skuValue", sku);
        List<CkProductData> data = sourceSession.selectList("com.example.CkSourceMapper.selectCkProductRandom", params);
        System.out.println(data);






        // 构建HashMap数据 形成传参
//
//        String sku = "1013230268411";
//        Map<String, Object> params = new HashMap<>();
//        params.put("skuList", skuList);
//        params.put("statusValue", status);
//        System.out.println(params);

        //使用Map查询 select columns_x from table where columns_y in skuList
//        selectProductMap(sourceSession);
//        System.out.println("使用Map查询 select columns_x from table where columns_y in skuList");







        //释放资源
        sourceSession.close();


    }

    /**
     * 使用Map 中的Hashmap执行xml配置查询得到产品信息表
     * @param sourceSession 数据库源连接Session 用于数据读取 使用完毕记得关闭
     */
    public static void selectProductMap(SqlSession sourceSession) {
        //执行查询
        // String sku = "1021230154611"  1021230154611  1013230268411;
        // 参数1构建 List类型
        List<String> skuList = new ArrayList<>();
        skuList.add("1021230154611");
        skuList.add("1013230268411");
        skuList.add("1019230041211");
        skuList.add("1019230041411");
        skuList.add("1021230164411");
        // 参数2传入
        String status = "待修改";
        // 使用HashMap 匹配出对应的类型数据
        Map<String, Object> params = new HashMap<>();
        params.put("skuList", skuList);
        params.put("statusValue", status);
        // 执行查询
        List<CkProductData> dataListIn = sourceSession.selectList("com.example.CkSourceMapper.selectCkProductMap", params);
        for (CkProductData p : dataListIn) {
            System.out.println(p);
        }
    }

    /**
     * 不使用Map传参 直接collection 写list 否则就map 对应参数名字
     * @param sourceSession 数据库源连接Session 用于数据读取 使用完毕记得关闭
     */
    public static void selectProductIn(SqlSession sourceSession) {
        //执行查询
        // String sku = "1021230154611"  1021230154611  1013230268411;
        // 参数1构建 List类型
        List<String> skuList = new ArrayList<>();
        skuList.add("1021230154611");
        skuList.add("1013230268411");
        skuList.add("1019230041211");
        skuList.add("1019230041411");
        skuList.add("1021230164411");
        List<CkProductData> dataListIn = sourceSession.selectList("com.example.CkSourceMapper.selectCkProductInfo", skuList);
        for (CkProductData p : dataListIn) {
            System.out.println(p);
        }
    }

}
