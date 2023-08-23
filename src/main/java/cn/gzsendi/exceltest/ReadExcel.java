package cn.gzsendi.exceltest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\Administrator\\Desktop\\成都十部--刊登明细数据汇总--初版\\center_data_all_syn.xlsx";
        ReadExelFile(path);
    }


    public static void ReadExelFile(String path) throws IOException {

        FileInputStream excelFile = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0); // Assuming you're working with the first sheet

        int sheetRowNumber = sheet.getLastRowNum();
        // 按照行读取数据
        for (int rowNum = 0; rowNum <= 10; rowNum++) {
            Row row = sheet.getRow(rowNum);//Row表示每一行的数据

            int minColIx = row.getFirstCellNum();
            int maxColIx = row.getLastCellNum();
            StringBuffer r = new StringBuffer();
            for (int i = 0; i < maxColIx; i++) {

                r.append(row.getCell(i)).append("\t");

            }
            System.out.println(r);


        }

    }
}
