package amazon.center.ckdata;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.*;



public class GetCKData {

    public static void main(String[] args) {
        ArrayList<ArrayList<Object>> res = Dbutils.GetCon();
        // 配置数据库信息链接
        assert res != null;
        ArrayList<Object> con16 = res.get(1); // CK数据库连
        String username = con16.get(0).toString();
        String password = con16.get(1).toString();
        String jdbcUrl = con16.get(2).toString();
        System.out.println(username);
        // 1、根据产品线查询对应的得产品信息数据
    }



    public static void CkProductLine(String jdbcUrl, String username, String password) {


        // 查询存储
        try {
            // Register the ClickHouse JDBC driver
            //Class.forName("ru.yandex.clickhouse.ClickHouseDriver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a query
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

            ResultSet resultSet = statement.executeQuery(query);

            // Create a new Excel workbook
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Data");
            //['spu','sku', 'title', 'new_price', 'product_length','product_height','product_width','product_weight_gross','end_time','path_name', 'resource_type', 'product_sx', 'product_status','line1','line2', 'line3','line4']
            // 构建表头遍历
            List<String> columns = new ArrayList<String>(){{
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
            System.out.println(columns);

            // Add header row
            Row headerRow = sheet.createRow(0);
            for(int i = 0; i < columns.size();i++){
                headerRow.createCell(i).setCellValue(columns.get(i));
            }
            // Add data rows  // Process the query result
            int rowNum = 1;
            while (resultSet.next()) {
                Row dataRow = sheet.createRow(rowNum++);
                // 遍历提取列长度
                for(int i = 0; i < columns.size();i++){
                    dataRow.createCell(i).setCellValue(resultSet.getString(columns.get(i)));
                }
            }

            // Save the workbook to an Excel file
            String SavePath = "C:\\Users\\Administrator\\Desktop\\产品线数据.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(SavePath)) {
                workbook.write(outputStream);
            }

            //Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Error ;=========" + e);


        }
    }



}
