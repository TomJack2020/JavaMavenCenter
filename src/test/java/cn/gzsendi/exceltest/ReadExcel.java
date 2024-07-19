package cn.gzsendi.exceltest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcel {


    public String path = "C:\\Users\\Administrator\\Desktop\\amazon_account_dep_new.xlsx";
    @Test
    public void tsReadexcel() throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(path));
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
