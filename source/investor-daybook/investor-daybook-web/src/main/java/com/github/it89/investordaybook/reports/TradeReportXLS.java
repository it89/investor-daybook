package com.github.it89.investordaybook.reports;

import com.github.it89.investordaybook.model.daybook.DealBond;
import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.model.daybook.TradeOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

public class TradeReportXLS {
    private TradeReportXLS() {
    }

    public static Workbook createWorkbook(List<DealStock> dealStocks, List<DealBond> dealBonds) {
        Workbook book = new XSSFWorkbook();
        createSheetDealStock(book, dealStocks);
        createSheetDealBond(book, dealBonds);
        return book;
    }

    private static void createSheetDealStock(Workbook book, List<DealStock> dealStocks) {
        final int NUMBER_COL = 0;
        final int SECURITY_NAME_COL = 1;
        final int DATE_COL = 2;
        final int TIME_COL = 3;
        final int AMOUNT_COL = 4;
        final int PRICE_COL = 5;
        final int VOLUME_COL = 6;
        final int COMMISSION_COL = 7;
        final int CASH_FLOW_COL = 8;
        final int STAGE_COL = 9;

        Sheet sheet = book.createSheet("Stocks");

        int rowNumber = 0;
        Row row = sheet.createRow(rowNumber);
        row.createCell(NUMBER_COL).setCellValue("Number");
        row.createCell(SECURITY_NAME_COL).setCellValue("Security");
        row.createCell(DATE_COL).setCellValue("Date");
        row.createCell(TIME_COL).setCellValue("Time");
        row.createCell(AMOUNT_COL).setCellValue("Amount");
        row.createCell(PRICE_COL).setCellValue("Price");
        row.createCell(VOLUME_COL).setCellValue("Volume");
        row.createCell(COMMISSION_COL).setCellValue("Commission");
        row.createCell(CASH_FLOW_COL).setCellValue("Cash flow");
        row.createCell(STAGE_COL).setCellValue("Stage");

        for (DealStock deal: dealStocks) {
            long amount = deal.getAmount();
            BigDecimal volume = deal.getVolume();

            if (deal.getOperation() == TradeOperation.BUY) {
                volume = volume.negate();
            } else {
                amount = - amount;
            }
            row = sheet.createRow(++rowNumber);
            row.createCell(NUMBER_COL).setCellValue(deal.getDealNumber());
            row.createCell(SECURITY_NAME_COL).setCellValue(deal.getSecurity().getCaption());
            row.createCell(DATE_COL).setCellValue(deal.getDateTime().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            row.createCell(TIME_COL).setCellValue(deal.getDateTime().toLocalTime().toString());
            row.createCell(AMOUNT_COL).setCellValue(amount);
            row.createCell(PRICE_COL).setCellValue(deal.getPrice().doubleValue());
            row.createCell(VOLUME_COL).setCellValue(volume.doubleValue());
            row.createCell(COMMISSION_COL).setCellValue(deal.getCommission().negate().doubleValue());
            row.createCell(CASH_FLOW_COL).setCellValue(deal.getCashFlow().doubleValue());
            if (deal.getStage() != null) {
                row.createCell(STAGE_COL).setCellValue(deal.getStage());
            }
        }

        for(int i = 0; i <= STAGE_COL; i++)
            sheet.autoSizeColumn(i);
    }

    private static void createSheetDealBond(Workbook book, List<DealBond> dealBonds) {
        final int NUMBER_COL = 0;
        final int SECURITY_NAME_COL = 1;
        final int DATE_COL = 2;
        final int TIME_COL = 3;
        final int AMOUNT_COL = 4;
        final int PRICE_COL = 5;
        final int VOLUME_COL = 6;
        final int COMMISSION_COL = 7;
        final int ACY_COL = 8;
        final int CASH_FLOW_COL = 9;
        final int STAGE_COL = 10;

        Sheet sheet = book.createSheet("Bonds");

        int rowNumber = 0;
        Row row = sheet.createRow(rowNumber);
        row.createCell(NUMBER_COL).setCellValue("Number");
        row.createCell(SECURITY_NAME_COL).setCellValue("Security");
        row.createCell(DATE_COL).setCellValue("Date");
        row.createCell(TIME_COL).setCellValue("Time");
        row.createCell(AMOUNT_COL).setCellValue("Amount");
        row.createCell(PRICE_COL).setCellValue("Price");
        row.createCell(VOLUME_COL).setCellValue("Volume");
        row.createCell(ACY_COL).setCellValue("ACY");
        row.createCell(COMMISSION_COL).setCellValue("Commission");
        row.createCell(CASH_FLOW_COL).setCellValue("Cash flow");
        row.createCell(STAGE_COL).setCellValue("Stage");

        for (DealBond deal: dealBonds) {
            long amount = deal.getAmount();
            BigDecimal volume = deal.getVolume();
            BigDecimal acy = deal.getAccumulatedCouponYield();

            if (deal.getOperation() == TradeOperation.BUY) {
                volume = volume.negate();
                acy = acy.negate();
            } else {
                amount = - amount;
            }
            row = sheet.createRow(++rowNumber);
            row.createCell(NUMBER_COL).setCellValue(deal.getDealNumber());
            row.createCell(SECURITY_NAME_COL).setCellValue(deal.getSecurity().getCaption());
            row.createCell(DATE_COL).setCellValue(deal.getDateTime().toLocalDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            row.createCell(TIME_COL).setCellValue(deal.getDateTime().toLocalTime().toString());
            row.createCell(AMOUNT_COL).setCellValue(amount);
            row.createCell(PRICE_COL).setCellValue(deal.getPricePct().doubleValue());
            row.createCell(VOLUME_COL).setCellValue(volume.doubleValue());
            row.createCell(ACY_COL).setCellValue(acy.doubleValue());
            row.createCell(COMMISSION_COL).setCellValue(deal.getCommission().negate().doubleValue());
            row.createCell(CASH_FLOW_COL).setCellValue(deal.getCashFlow().doubleValue());
            if (deal.getStage() != null) {
                row.createCell(STAGE_COL).setCellValue(deal.getStage());
            }
        }

        for(int i = 0; i <= STAGE_COL; i++)
            sheet.autoSizeColumn(i);
    }
}
