package cn.gzsendi.exceltest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadCsv {


    public static void main(String[] args) {
        String path = "C:\\Users\\Administrator\\Desktop\\成都十部--刊登明细数据汇总--初版\\center_data_all_create_time.csv";
//        String path = "C:\\Users\\Administrator\\Desktop\\成都十部--刊登明细数据汇总--初版\\center_data_all_syn.csv";


        try {
            InputStream file = Files.newInputStream(Paths.get(path));
            DataInputStream in = new DataInputStream(file);
            BufferedReader br= new BufferedReader(new InputStreamReader(in,"GBK"));
            String line;
            int n = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                n ++;
                // Process the values array as needed
//                for (String value : values) {
//                    System.out.print(value + "\t");
//                }
//                System.out.println(); // Move to the next line
            }
            System.out.println("all rows " + n);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}








