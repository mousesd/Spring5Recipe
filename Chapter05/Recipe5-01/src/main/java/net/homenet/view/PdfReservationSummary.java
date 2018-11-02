package net.homenet.view;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import net.homenet.domain.Reservation;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class PdfReservationSummary extends AbstractPdfView {
    @Override
    @SuppressWarnings("unchecked")
    protected void buildPdfDocument(Map<String, Object> model
        , Document document
        , PdfWriter writer
        , HttpServletRequest request
        , HttpServletResponse response) throws Exception {

        Table table = new Table(5);
        table.addCell("Court Name");
        table.addCell("Date");
        table.addCell("Hour");
        table.addCell("Player Name");
        table.addCell("Player Phone");

        final List<Reservation> reservations = (List<Reservation>) model.get("reservations");
        for (Reservation reservation : reservations) {
            table.addCell(reservation.getCourtName());
            table.addCell(reservation.getDate().toString());
            table.addCell(Integer.toString(reservation.getHour()));
            table.addCell(reservation.getPlayer().getName());
            table.addCell(reservation.getPlayer().getPhone());
        }
        document.add(table);
    }
}
