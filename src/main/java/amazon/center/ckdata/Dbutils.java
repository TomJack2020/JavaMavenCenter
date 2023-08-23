package amazon.center.ckdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        Connection res = ConnectDb("1","2","3");
    }

}
