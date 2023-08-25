package amazon.center.ckdata;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Dbutils {


    /**
     * 数据库连接
     * @param jdbcUrl
     * @param username
     * @param password
     * @return
     */
    public static Connection ConnectDb(String jdbcUrl, String username, String password){

        try {
            // Register the ClickHouse JDBC driver
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            return connection;
        } catch (SQLException e1) {
            System.out.println(e1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * 执行查询返回result
     * @param con
     * @param sql
     * @return
     */
    public static ResultSet QuerySql(Connection con, String sql){
        try {
            Statement statement = con.createStatement();
            return statement.executeQuery(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据写入到本地excel
     * @param SavePath
     * @param columns
     * @param result
     * @throws SQLException
     */
    public static void WriteToExcel(String SavePath, ArrayList<String> columns, ResultSet result) throws Exception {
        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet0");
        // Add header row
        Row headerRow = sheet.createRow(0);
        for(int i = 0; i < columns.size();i++){
            headerRow.createCell(i).setCellValue(columns.get(i));
        }

        // Add data rows  // Process the query result
        int rowNum = 1;
        while (result.next()) {
            Row dataRow = sheet.createRow(rowNum++);
            // 遍历提取列长度
            for(int i = 0; i < columns.size();i++){
                dataRow.createCell(i).setCellValue(result.getString(columns.get(i)));
            }
        }
        // Save the workbook to an Excel file
//        String SavePath = "C:\\Users\\Administrator\\Desktop\\产品线数据.xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(SavePath)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }


    /**
     * 获取数据库连接信息数据
     * @return
     */
    public static ArrayList<ArrayList<Object>> GetCon()   {
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            props.load(fis);
            fis.close();

            //Amazon销售中台连接信息
            ArrayList<Object> res1 = new ArrayList<Object>(){{
                add(props.getProperty("UserName"));
                add(props.getProperty("PassWord"));
                add(props.getProperty("url16"));
            }};

            // Ck数据库连接信息
            ArrayList<Object> res2 = new ArrayList<Object>(){{
                add(props.getProperty("UserNameCk"));
                add(props.getProperty("PassWordCk"));
                add(props.getProperty("urlCk"));
            }};

            // Ck数据库连接信息
            ArrayList<Object> res3 = new ArrayList<Object>(){{
                add(props.getProperty("UserName"));
                add(props.getProperty("PassWord"));
                add(props.getProperty("url30"));
            }};


            // 数据增加到ArrayList
            ArrayList<ArrayList<Object>> res_li = new ArrayList<>();
            res_li.add(res1);  // Amazon销售中台
            res_li.add(res2);  // CK 数据分析
            res_li.add(res3); // 易百产品系统
            return res_li;

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

}
