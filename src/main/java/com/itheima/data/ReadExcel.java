package com.itheima.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;


class ReadExcel {
    public static void main(String[] args) throws IOException {
        System.out.println("测试读取");
        String path = "C:\\Users\\Administrator\\Desktop\\武汉六部-2024.6月刊登不达标-7.16.xlsx";
        FileInputStream excelFile = new FileInputStream(path);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(2); // Assuming you're working with the first sheet

//        int sheetRowNumber = sheet.getLastRowNum();
        // 按照行读取数据
        for (int rowNum = 0; rowNum <= 100; rowNum++) {
            Row row = sheet.getRow(rowNum);//Row表示每一行的数据

//            int minColIx = row.getFirstCellNum();
            int maxColIx = row.getLastCellNum();
            StringBuffer r = new StringBuffer();
            for (int i=0; i < maxColIx; i++){
                r.append(row.getCell(i)).append("\t");

            }
            System.out.println(r);


//            List<String> rowList = new ArrayList<>();
//            //遍历该行，并获取每一个cell的数据
//            for (int colIx = minColIx; colIx < maxColIx; colIx++) {
//                Cell cell = row.getCell(colIx);
//                System.out.println(cell+ "\t");
//
//            }

        }


    }
}