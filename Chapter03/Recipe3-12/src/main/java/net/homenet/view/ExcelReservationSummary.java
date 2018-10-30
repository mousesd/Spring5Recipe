package net.homenet.view;

import net.homenet.domain.Reservation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ExcelReservationSummary extends AbstractXlsxView {
    @Override
    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model
        , Workbook workbook
        , HttpServletRequest request
        , HttpServletResponse response) {

        final List<Reservation> reservations = (List<Reservation>) model.get("reservations");
        final Sheet sheet = workbook.createSheet();

        addHeaderRow(sheet);

        reservations.forEach(reservation -> createRow(sheet, reservation));
    }

    private void addHeaderRow(Sheet sheet) {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Court Name");
        header.createCell(1).setCellValue("Date");
        header.createCell(2).setCellValue("Hour");
        header.createCell(3).setCellValue("Player Name");
        header.createCell(4).setCellValue("Player Phone");
    }

    private void createRow(Sheet sheet, Reservation reservation) {
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        row.createCell(0).setCellValue(reservation.getCourtName());
        row.createCell(1).setCellValue(reservation.getDate().toString());
        row.createCell(2).setCellValue(reservation.getHour());
        row.createCell(3).setCellValue(reservation.getPlayer().getName());
        row.createCell(4).setCellValue(reservation.getPlayer().getPhone());
    }
}
