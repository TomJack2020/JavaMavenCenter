package amazon.center;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {


    @Test
    public void ReadConfig() {
        Properties props = new Properties();
        try{
            FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            props.load(fis);
            fis.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        String user = props.getProperty("UserName");
        String pwd = props.getProperty("PassWord");
        String url = props.getProperty("url16");
//        System.out.printf("%s %S %s", user, pwd, url);
        System.out.println("info");
    }

}
