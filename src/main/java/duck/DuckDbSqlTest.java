package duck;

import java.sql.*;
import java.util.Properties;


public class DuckDbSqlTest {

    public static ResultSet getResultSet(String dbPath, String sql_str) throws ClassNotFoundException, SQLException {

        // 加载驱动
        try {
            Class.forName("org.duckdb.DuckDBDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 创建连接
        Properties readOnlyProperty = new Properties();
        readOnlyProperty.setProperty("duckdb.read_only", "true");

        // 连接

        Connection conn = DriverManager.getConnection(dbPath, readOnlyProperty);
        // 创建Statement
        Statement stmt = conn.createStatement();
        // 执行sql
        try {
            return stmt.executeQuery(sql_str);     // 返回结果集
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 关闭连接
        conn.close();
        return null;


    }





//
//    // 读取sql文件私有方法
//    public static String readSqlFile(String fileName) {
//        StringBuilder sql = new StringBuilder();
//        try {
//            java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(fileName));
//            String s = "";
//            while ((s = br.readLine()) != null) {
//                sql.append(s).append("\n");
//            }
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sql.toString();
//    }




}
