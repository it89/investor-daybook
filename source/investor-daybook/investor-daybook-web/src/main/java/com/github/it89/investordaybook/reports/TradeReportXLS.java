package com.github.it89.investordaybook.reports;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;

public class TradeReportXLS {
    public static Workbook createWorkbook() throws IOException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("Deals");

        Row row = sheet.createRow(1);
        row.createCell(1).setCellValue("Security");
        row.createCell(2).setCellValue("Date");
        row.createCell(3).setCellValue("Amount");
        row.createCell(4).setCellValue("Price");
        row.createCell(5).setCellValue("Volume");
        row.createCell(6).setCellValue("Comission");
        row.createCell(7).setCellValue("NKD");

        return book;
    }
}
