package amazon.center;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GetConnect {
    /**
     *
     * @return 返回数据库链接信息
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



            ArrayList<ArrayList<Object>> res_li = new ArrayList<>();
            res_li.add(res1);
            res_li.add(res2);
            return res_li;

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
