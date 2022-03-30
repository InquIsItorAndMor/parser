/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.sokrat.sokratparsersj.models.Vacancie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dimay
 */
public class Excel {
    
    Environment env;
    
    public Excel(Environment current) {
        this.env = current;
    }
    
    void copyFile() throws Exception {
        InputStream template = new FileInputStream(env.getProperty("PATH_TEMPLATE_EXCEL"));
        Workbook wb = WorkbookFactory.create(template);

        //Saving excel to a different location or filename. 
        FileOutputStream out = new FileOutputStream(env.getProperty("PATH_SAVE_EXCEL") + "\\123.xlsx" );
        wb.write(out);
        wb.close();
        out.close();
        template.close();
    }
    
    public void writeIntoExcel( List<Vacancie> vacancies) throws FileNotFoundException, IOException, Exception{
        copyFile();
        try(FileInputStream file = new FileInputStream(new File(env.getProperty("PATH_SAVE_EXCEL") + "\\123.xlsx"))) {
            Workbook book = new XSSFWorkbook(file);

            //Workbook book = new HSSFWorkbook();
            Sheet sheet = book.getSheetAt(0);

            // Нумерация начинается с нуля
            //Row row = sheet.createRow(0);

            // Мы запишем имя и дату в два столбца
            // имя будет String, а дата рождения --- Date,
            // формата dd.mm.yyyy
            String str = env.getProperty("COUNT_CELL_EXCEL");
            ArrayList<String> countCell = (new Gson()).fromJson(env.getProperty("COUNT_CELL_EXCEL"), ArrayList.class) ;
            try{
                short height = 14;
                sheet.setDefaultRowHeight(height);
                for(int indexVacancie = 1; indexVacancie < vacancies.size(); indexVacancie++) {
                    Row row = sheet.createRow(indexVacancie);
                    List<String> vacancie = vacancies.get(indexVacancie - 1).getObjectToArray();
                    for(int indexCell = 0; indexCell < countCell.size(); indexCell++) {
                        Cell cell = row.createCell(CellReference.convertColStringToIndex(countCell.get(indexCell)));
                        cell.getCellStyle().setWrapText(true);
                        cell.setCellValue(vacancie.get(indexCell));
                    }
                }
            } catch(Exception ex) {}


          /*  Cell name = row.getCell(0);
            name.setCellValue("John");

            Cell birthdate = row.createCell(1);

            DataFormat format = book.createDataFormat();
            CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
            birthdate.setCellStyle(dateStyle);


            // Нумерация лет начинается с 1900-го
            birthdate.setCellValue(new Date(110, 10, 10));

            // Меняем размер столбца
            sheet.autoSizeColumn(1);*/

            // Записываем всё в файл
            book.write(new FileOutputStream(env.getProperty("PATH_SAVE_EXCEL") + "\\123.xlsx"));
            book.close();
        }
    }
}
