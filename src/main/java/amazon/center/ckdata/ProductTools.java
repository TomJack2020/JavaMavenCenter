package amazon.center.ckdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductTools {
    public static void main(String[] args) throws Exception{
        long t0 = System.currentTimeMillis();
        // CK数据库连
        ArrayList<Object> ck = Dbutils.GetCon().get(1);
        // 连接数据库
        Connection connection = Dbutils.ConnectDb(ck.get(2).toString(), ck.get(0).toString(), ck.get(1).toString());
        // 构建查询
        String query = "SELECT a.spu as spu ,\n" +
                "b.sku as sku , \n" +
                "a.title_cn as title_cn,\n" +
                "b.new_price as new_price,\n" +
                "b.pur_length_pack as product_length,\n" +
                "b.pur_height_pack as product_height,\n" +
                "b.pur_width_pack as product_width,\n" +
                "b.pur_weight_pack as product_weight_gross,\n" +
                "a.end_time as end_time, \n" +
                "d.path_name as path_name,\n" +
                "CASE\n" +
                "WHEN b.resource_type = 1 THEN '正常'\n" +
                "WHEN b.resource_type = 2 THEN '停产' \n" +
                "WHEN b.resource_type = 3 THEN '缺货 '\n" +
                "WHEN b.resource_type = 3 THEN '停产找货中 '\n" +
                "ELSE '其他'\n" +
                "END AS resource_type,\n" +
                "CASE\n" +
                "WHEN a.product_is_multi = 0 THEN '普通单品'\n" +
                "WHEN a.product_is_multi = 1 THEN '多属性单品'\n" +
                "WHEN a.product_is_multi = 3 THEN '捆绑'\n" +
                "WHEN a.product_is_multi = 4 THEN '组合'\n" +
                "END AS product_sx,\n" +
                "CASE\n" +
                "WHEN a.product_status = 0 THEN '已创建'\n" +
                "WHEN a.product_status = 1 THEN '待修改'\n" +
                "WHEN a.product_status = 2 THEN '待修改'\n" +
                "WHEN a.product_status = 3 THEN '待买样'\n" +
                "WHEN a.product_status = 4 THEN '待品检'\n" +
                "WHEN a.product_status = 5 THEN '待编辑'\n" +
                "WHEN a.product_status = 6 THEN '待拍摄'\n" +
                "WHEN a.product_status = 7 THEN '待编辑待拍摄'\n" +
                "WHEN a.product_status = 8 THEN '待修图'\n" +
                "WHEN a.product_status = 9 THEN '在售中'\n" +
                "WHEN a.product_status = 10 THEN '审核不通过'\n" +
                "WHEN a.product_status = 11 THEN '停售'\n" +
                "WHEN a.product_status = 12 THEN '待清仓'\n" +
                "WHEN a.product_status = 13 THEN '已滞销'\n" +
                "WHEN a.product_status = 14 THEN '待物流审核'\n" +
                "WHEN a.product_status = 15 THEN '待关务审核'\n" +
                "WHEN a.product_status = 16 THEN '文案驳回到开发 '\n" +
                "WHEN a.product_status = 17 THEN '文案驳回品控'\n" +
                "WHEN a.product_status = 18 THEN '摄影驳回到开发'\n" +
                "WHEN a.product_status = 19 THEN '摄影驳回到品控'\n" +
                "WHEN a.product_status = 20 THEN '取消开发'\n" +
                "WHEN a.product_status = 21 THEN '侵权待审核'\n" +
                "WHEN a.product_status = 22 THEN '待终审'\n" +
                "WHEN a.product_status = 23 THEN 'ECN资料变更中'\n" +
                "WHEN a.product_status = 24 THEN 'ECN资料变更驳回'\n" +
                "END as product_status,\n" +
                "splitByChar('|', replaceAll(d.path_name,'>>', '|'))[1] as line1,\n" +
                "splitByChar('|', replaceAll(d.path_name,'>>', '|'))[2] as line2,\n" +
                "splitByChar('|', replaceAll(d.path_name,'>>', '|'))[3] as line3,\n" +
                "splitByChar('|', replaceAll(d.path_name,'>>', '|'))[4] as line4\n" +
                "FROM yibai_prod_base_sync.yibai_prod_spu a\n" +
                "LEFT JOIN yibai_prod_base_sync.yibai_prod_sku b\n" +
                "ON a.spu = b.spu     \n" +
                "LEFT JOIN yb_datacenter.yb_product c \n" +
                "ON c.sku = b.sku\n" +
                "LEFT JOIN yb_datacenter.yb_product_linelist d\n" +
                "ON toInt64(d.id) = c.product_linelist_id\n" +
                "WHERE line3 = '照明附件'";


        // 返回查询结果
        assert connection != null;
        ResultSet result =  Dbutils.QuerySql(connection,query);

        ArrayList<String> columns;
        columns = new ArrayList<String>(){{
            add("spu");
            add("spu");
            add("title_cn");
            add("new_price");
            add("product_length");
            add("product_width");
            add("product_weight_gross");
            add("end_time");
            add("path_name");
        }};

        // 数据存储
        String SavePath = "data\\line3.xlsx";
        Dbutils.WriteToExcel(SavePath, columns,result);

        result.close();
        connection.close();
        // 耗时
        long t1 = System.currentTimeMillis();
        System.out.printf("User time %d ms", (t1 - t0));
    }
}
