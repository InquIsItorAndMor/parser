/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sokrat.sokratparsersj.util;

import com.google.gson.Gson;
import com.sokrat.sokratparsersj.models.DownloadFile;
import com.sokrat.sokratparsersj.models.Vacancie;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public DownloadFile writeIntoExcel( List<Vacancie> vacancies, String catalog) throws FileNotFoundException, IOException, Exception{
        copyFile();
        try(FileInputStream file = new FileInputStream(new File(env.getProperty("PATH_SAVE_EXCEL") + "\\123.xlsx"))) {
            Workbook book = new XSSFWorkbook(file);
            Sheet sheet = book.getSheetAt(0);
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
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            // Записываем всё в файл
            String fileName = "catalog_" + catalog + "_" + dateFormat.format(date) + ".xlsx";
            String pathOutputFile = env.getProperty("PATH_SAVE_EXCEL") + "\\";
            book.write(new FileOutputStream(pathOutputFile + fileName));
            book.close();
            
            DownloadFile dFile = new DownloadFile();
            dFile.setFileName(fileName);
            dFile.setPath(pathOutputFile);
            return dFile;
        }
    }
}
