import duck.DuckDbSqlTest;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDuckdb {


    @Test
    public void test() throws SQLException, ClassNotFoundException {
        // write your test code here
        String dbUrl = "jdbc:duckdb:D:\\DuckdbTemplate\\amazon_rate10.db";

        String sql = "show tables";

        // execute the SQL statement
        ResultSet result= DuckDbSqlTest.getResultSet(dbUrl, sql);


        while (true) {
            assert result != null;
            if (!result.next()) break;
            System.out.println(result.getString(1));
        }

        System.out.println();



    }

    // do other tests here
}
