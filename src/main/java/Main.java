import com.itheima.CkSyn;
import com.itheima.pojo.CkProductData;
import org.apache.ibatis.session.SqlSession;
import com.itheima.pojo.CkProductData;
import com.itheima.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {




    public static void main(String[] args) throws IOException {


        // 数据源配置mybatis
        String SourceMapper = "mybatis_source_ck_config.xml";
        InputStream inputStreamSource;
        inputStreamSource = Resources.getResourceAsStream(SourceMapper);
        SqlSessionFactory sourceSqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStreamSource);
        SqlSession sourceSession = sourceSqlSessionFactory.openSession();

        // 使用In模式读取数据 未使用map
        System.out.println("使用In模式读取数据 未使用map");
        CkSyn.selectProductIn(sourceSession); //调用包数据





        // 随机字段嵌入查询
        System.out.println("随机字段嵌入查询");
        // 自定义但字段In Eg： select * from table where column in list;
        String column = "line3";
        ArrayList<String> columnValueList = new ArrayList<String>();
        columnValueList.add("照明附件");
        columnValueList.add("工业控制元件");
        columnValueList.add("增材制造产品");

        ArrayList<CkProductData> res = CkSyn.selectProductRandomColumn(sourceSession, column, columnValueList);
        System.out.println("查询结果：\n" + res);
        System.out.printf("查询结果行数 %s 条", res.size());





        // 关闭资源
        sourceSession.close();
        inputStreamSource.close();



    }






}
