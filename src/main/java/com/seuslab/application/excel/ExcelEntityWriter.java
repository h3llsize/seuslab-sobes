package com.seuslab.application.excel;

import com.seuslab.application.entity.UserEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ExcelEntityWriter {

    private final Workbook workbook = new HSSFWorkbook();

    @Value("${service.file_name}")
    private String fileName;

    private final Sheet sheet = workbook.createSheet();

    private volatile int counter = 0;

    public void writeToExcel(UserEntity userEntity) {
        Row row = sheet.createRow(counter);

        row.createCell(0).setCellValue(userEntity.getId());
        row.createCell(1).setCellValue(userEntity.getFirstName());
        row.createCell(2).setCellValue(userEntity.getLastName());
        row.createCell(3).setCellValue(userEntity.getBirthDate());
        row.createCell(4).setCellValue(userEntity.getCity());
        row.createCell(5).setCellValue(userEntity.getContacts());
    }

    public void buildFile() {
        try (FileOutputStream fileOut = new FileOutputStream(fileName + ".xls")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
