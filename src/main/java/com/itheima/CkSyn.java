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

        // 使用HashMap 匹配出对应的类型数据 使用Map查询 select columns_x from table where columns_y in skuList
        System.out.println("使用HashMap 匹配出对应的类型数据");
        selectProductMap(sourceSession);

        // 随机字段嵌入查询
        System.out.println("随机字段嵌入查询");
        // 自定义但字段In Eg： select * from table where column in list;
        String column = "line3";
        ArrayList<String> columnValueList = new ArrayList<String>();
        columnValueList.add("照明附件");
        columnValueList.add("工业控制元件");
        columnValueList.add("增材制造产品");

        ArrayList<CkProductData> res = selectProductRandomColumn(sourceSession, column, columnValueList);
        System.out.printf("查询结果行数 %s 条", res.size());

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


    public static ArrayList<CkProductData> selectProductRandomColumn(SqlSession sourceSession, String column, ArrayList<String> columnValueList){
        try{

            // 构建入参
            Map<String, Object> params = new HashMap<>();
            params.put("column", column);
            params.put("columnValueList", columnValueList);
            System.out.printf("随机字段的值为 %s  列表值/元组值为 %s \n", column, columnValueList);
            // 执行Sql
            List<CkProductData> dataProductRand = sourceSession.selectList("com.example.CkSourceMapper.selectCkProductRandom", params);

            return (ArrayList<CkProductData>) dataProductRand;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
