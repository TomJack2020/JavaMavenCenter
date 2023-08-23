package amazon.center;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AmazonCenter {

    @Test
    public void ReadCenterSuccess() {
        ArrayList<ArrayList<Object>> res = GetConnect.GetCon();

        // 配置数据库信息链接
        assert res != null;
        ArrayList<Object> con16 = res.get(0); // 中台数据库连
        String user = con16.get(0).toString() ;
        String pwd = con16.get(1).toString() ;
        String url16 = con16.get(2).toString() ;
        //调用函数查询
        GetSqlToExcel(url16, user, pwd, 31142);
        System.out.println("Read Successful");


    }

        public void  GetSqlToExcel(String jdbcUrl, String username, String password , int account_id_x) {
            String start_date = "2023-08-01";
            String end_date = "2023-08-21";
//            int account_id_x  = 31013;
            try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)){
                String query = "select account_id, spu,sku, seller_sku,brand_name,sale_by, created_by ,\n" +
                        "'刊登失败' as publish_status,\n" +
                        "group_concat(error_code SEPARATOR '|') as publish_fail_error_code,\n" +
                        "group_concat(message SEPARATOR '|') as publish_fail_message,\n" +
                        "FROM_UNIXTIME(last_review_time) as syn_time\n" +
                        "from (\n" +
                        "select a.publish_id, a.sku as spu ,a.sale_by , a.created_by , a.account_id,\n" +
                        "b.brand_name ,if(c.sku is null, b.sku, c.sku) as sku,\n" +
                        "if(c.item_sku is null , b.item_sku, c.item_sku) as seller_sku,\n" +
                        "CASE a.publish_type\n" +
                        "WHEN 1 THEN \"系统生成\"\n" +
                        "WHEN 2 THEN \"常规刊登\"\n" +
                        "WHEN 3 THEN \"JP清关补刊\"\n" +
                        "WHEN 4 THEN \"人工导入\"\n" +
                        "WHEN 5 THEN \"算法刊登\"\n" +
                        "WHEN 6 THEN \"链接翻新\"\n" +
                        "WHEN 7 THEN \"复制刊登\"\n" +
                        "ELSE \"其他\"\n" +
                        "END as source_type,\n" +
                        "d.error_code,\n" +
                        "d.message,\n" +
                        "a.last_review_time\n" +
                        "from yibai_sale_center_amazon.yibai_amazon_publish_ready_failed a\n" +
                        "left join (select * from yibai_sale_center_amazon.yibai_amazon_publish_task where account_id =31013) b\n" +
                        "on a.publish_id  = b.publish_id\n" +
                        "left join (select * from yibai_sale_center_amazon.yibai_amazon_publish_task_variation where account_id =31013) c\n" +
                        "on a.publish_id  = c.publish_id\n" +
                        "left join (SELECT error_code,publish_id, message\n" +
                        "FROM yibai_sale_center_amazon.yibai_amazon_publish_fail_message_list) d\n" +
                        "on a.publish_id = d.publish_id\n" +
                        "where a.last_review_time BETWEEN UNIX_TIMESTAMP('2023-08-01') AND UNIX_TIMESTAMP('2023-08-21') and\n" +
                        "`type` = 2 and a.account_id =31013 GROUP BY sku, d.publish_id, d.error_code) f\n" +
                        "group by spu,sku, seller_sku,brand_name, sale_by, created_by, syn_time;";

//                System.out.println(query);

                try (Statement statement = con.createStatement();
                     ResultSet resultSet = statement.executeQuery(query)){

                    Workbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("Success Data");

                    // 制作表头
                    Row row0 = sheet.createRow(0);
                    row0.createCell(0).setCellValue("sku");
                    row0.createCell(1).setCellValue("spu");
                    row0.createCell(2).setCellValue("brand_name");

                    int rowNumber = 1;
                    while (resultSet.next()) {
//                        System.out.println(resultSet.getString("sku"));
                        Row row = sheet.createRow(rowNumber++);
                        row.createCell(0).setCellValue(resultSet.getString("sku"));
                        row.createCell(1).setCellValue(resultSet.getString("spu"));
                        row.createCell(2).setCellValue(resultSet.getString("brand_name"));
                        // Add more columns as needed
                    }
                    //使用完毕关闭连接
                    statement.close();
                    con.close();

                    try (FileOutputStream outputStream = new FileOutputStream("success.xlsx")) {
                        workbook.write(outputStream);
                    } catch (IOException e){
                        System.out.println("输出文件错误=》" + e);
                    }
                }
            } catch (SQLException e) {
                System.out.println("数据库操作错误" + e);
            }
        }



}
