package com.duckdb.anay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DuckDbTestExample {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        long t0 = System.currentTimeMillis();
        Class.forName("org.duckdb.DuckDBDriver");

        // 创建连接
        Connection conn = DriverManager.getConnection("jdbc:duckdb:D:\\DuckdbTemplate\\amazon_rate10.db");
        Statement stmt = conn.createStatement();


        String sql1 = "show tables";
        ResultSet rs = stmt.executeQuery(sql1);

        while (rs.next()) {
            System.out.println(rs.getInt(1));

        }


        long t1 = System.currentTimeMillis();
        System.out.printf("Use Time %s ms! All rows: %s", (t1 - t0), 100);

    }


  
}
