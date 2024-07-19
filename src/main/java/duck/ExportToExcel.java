package duck;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ExportToExcel {


    /**
     * Export data from database to Excel file
     * @param rs ResultSet
     * @param out_path 输出路径
     * @throws Exception 异常
     */
    public static void DataToExcel(ResultSet rs, String out_path) throws Exception {

        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Create workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            // Create worksheet
            XSSFSheet sheet = workbook.createSheet("TestSheet");
            // Create header row
            XSSFRow headerRow = sheet.createRow(0);

            // Create header cells
            for (int i = 1; i <= columnCount; i++) {
                // Create header cells
                String cells_value = metaData.getColumnName(i);
                headerRow.createCell(i - 1).setCellValue(cells_value);
            }

            // Fill data rows
            int rowIndex = 1;
            while (rs.next()) {
                XSSFRow dataRow = sheet.createRow(rowIndex++);
                for (int i = 1; i <= columnCount; i++) {
                    String columnsName = metaData.getColumnName(i);
                    dataRow.createCell(i - 1).setCellValue(rs.getString(columnsName));
                }

                // Save Excel file
                FileOutputStream fos = new FileOutputStream(out_path);
                workbook.write(fos);
                fos.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Excel file created successfully!");
    }




}
