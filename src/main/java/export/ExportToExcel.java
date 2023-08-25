package export;

import amazon.center.ckdata.Dbutils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;



public class ExportToExcel {
    /**
     * 易百产品系统 指定标题钟含有什么汉字的spu/sku数据  例如: 跃星辉
     * @param ContainWord The word contain in the title
     * @return  ArrayList<Object>  ==>String + ArrayList
     */
    public static ArrayList<Object> Yxh(String ContainWord){
        ArrayList<String> columns;
        columns = new ArrayList<String>(){{
            add("sku");
            add("spu");
            add("title_cn");
            add("end_time");
            add("devp_type");
        }};

        String sql;
        sql = "select a.sku,a.spu, a.title_cn, b.end_time, a.devp_type \n" +
                "from yibai_prod_base.yibai_prod_sku a\n" +
                "left join yibai_prod_base.yibai_prod_spu b\n" +
                "on a.spu = b.spu \n" +
                "where a.title_cn like \"%" + ContainWord + "%\"";
        ArrayList<Object> arrayData = new ArrayList<>();
        arrayData.add(sql);
        arrayData.add(columns);
        return arrayData;
    }



    /**
     * 指定数值返回对应连接  0 =》Amazon销售中台; 1 =》CK库 ; 2 =>产品系统库
     * @param DbNum Db Connection Number
     * @return Connection
     */
    public static Connection ConDb (int DbNum){
        //   数据库连
        ArrayList<Object> db = Objects.requireNonNull(Dbutils.GetCon()).get(DbNum); // 连接数据库
        Connection connection;
        connection = Dbutils.ConnectDb(db.get(2).toString(), db.get(0).toString(), db.get(1).toString());
        return connection;
    }

    public static void main(String[] args) throws Exception{



        long t0 = System.currentTimeMillis();


        // 构建查询
        ArrayList<Object> li = Yxh("跃星辉");
        String sql = li.get(0).toString();

        @SuppressWarnings("unchecked") // 不使用类型警告信息
        ArrayList<String> columns =  (ArrayList<String>) li.get(1);

        // 返回查询结果
        Connection con = ConDb(2);
        ResultSet result =  Dbutils.QuerySql(con,sql);


        // 数据存储
        String SavePath = "data\\跃星辉产品.xlsx";
        Dbutils.WriteToExcel(SavePath, columns,result);

        result.close();
        con.close();
        // 耗时
        long t1 = System.currentTimeMillis();
        System.out.printf("User time %d ms", (t1 - t0));
    }

}
