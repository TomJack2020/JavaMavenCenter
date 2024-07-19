package duck;

import java.sql.*;
import java.util.Properties;


/**
 * DuckDbExecute
 * 自定义读取duckdb数据库的java代码
 * @author cep
 * @date 2024/07/17
 * @version 1.0.0
 */

public class DuckDbExecute {

    /**
     * 获取连接
     * @param dbIUrl
     * @return
     * @throws Exception
     */
    public static  Connection getConnDuck( String dbIUrl) throws Exception {
        // 加载驱动
        Class.forName("org.duckdb.DuckDBDriver");

        // 创建连接
        Properties readOnlyProperty = new Properties();
        readOnlyProperty.setProperty("duckdb.read_only", "true");
        // 返回连接
        return DriverManager.getConnection(dbIUrl, readOnlyProperty);
    }


    /**
     * 执行sql语句 需要返回结果集 格式化打印结果集
     * @param sql
     * @param conn
     * @return ResultSet
     * @throws Exception
     */
    public static ResultSet getResultSet(String sql, Connection conn) throws Exception {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", metaData.getColumnName(i));
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", rs.getObject(i));
            }
            System.out.println();

        }
        return rs;
    }



    /**
     * 关闭连接
     * @param conn
     * @throws Exception
     */
    public static void closeConnection(Connection conn) throws Exception {
        Statement stmt = conn.createStatement();
        stmt.close();
        conn.close();
    }






}
