import com.itheima.DataMigrateProductToCk;
import com.itheima.common.DbUtils;
import com.mythread.MyThread;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.itheima.common.DbUtils.synData;
import static com.itheima.common.DbUtils.test_read;

public class RunThread {



    public static void main(String[] args) throws IOException {


        // 调用函数清空目标库表
        DataMigrateProductToCk.TruncateTable("syn_yibai_prod_inf_country_grade");

        // 线程池 固定线程数量执行函数调度
        ExecutorService exec = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 3; i++) {
            exec.execute(new MyThread(i,"syn_yibai_prod_inf_country_grade")); // 根据名字调用不同的脚本
        }

        // 关闭线程池
        exec.shutdown();


        // 数据源配置mybatis
//        String SourceMapper = "mybatis_source_product_config.xml";
//        SqlSession sourceSession = DbUtils.getSqlSession(SourceMapper);
//
//
//        //目标数据源配置mybatis
//        String TargetMapper = "mybatis_target_localCk_config.xml";
//        SqlSession targetSession = DbUtils.getSqlSession(TargetMapper);
//
//
//        // 读取源数据 调用函数 同步数据
//        String sourceMapper = "com.example.ProductSourceMapper.selectInfoCountry";
//        String targetMapper = "com.example.ProductTargetListMapper.saveInfCountry";
//
//        test_read(sourceSession, targetSession, sourceMapper, targetMapper, 1);




    }
}
