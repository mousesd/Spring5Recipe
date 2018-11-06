package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.exception.ReservationWebException;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {
    private final ReservationService reservationService;

    public ReservationSummaryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //# 1.
    @GetMapping
    public String generateSummary(@RequestParam(value = "date") String date, Model model) {
        List<Reservation> reservations;
        try {
            LocalDate summaryDate = new SimpleDateFormat("yyyy-MM-dd")
                .parse(date)
                .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            reservations = reservationService.findByDate(summaryDate);
        } catch (ParseException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            throw new ReservationWebException("Invalid date format for reservation summary", new Date(), sw.toString());
        }

        model.addAttribute("reservations", reservations);
        return "reservationSummary";
    }

    //# 2.
    //@GetMapping
    //public String generateSummary(
    //    @RequestParam(value = "date")
    //    @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    //    , Model model)
    //{
    //    List<Reservation> reservations = reservationService.findByDate(date);
    //    model.addAttribute("reservations", reservations);
    //    return "reservationSummary";
    //}

    //@ExceptionHandler
    //public void handle(ServletRequestBindingException ex, @RequestParam(value = "date") String date) {
    //    //# 예외가 발생해도 이 메서드가 Callback 안됨!
    //    if (ex.getRootCause() instanceof ParseException) {
    //        StringWriter sw = new StringWriter();
    //        PrintWriter pw = new PrintWriter(sw);
    //        ex.getRootCause().printStackTrace(pw);
    //        throw new ReservationWebException("Invalid date format for reservation summary", new Date(), sw.toString());
    //    }
    //}
}
