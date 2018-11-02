package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.service.ReservationService;
import net.homenet.util.Delayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //@GetMapping
    //public String setupForm(Model model) {
    //    model.addAttribute("courtName", "");
    //    model.addAttribute("reservations", new ArrayList<Reservation>());
    //    return "reservationQuery";
    //}

    @GetMapping
    public void setupForm() { }

    //@PostMapping
    //public String submitForm(@RequestParam("courtName") String courtName, Model model) {
    //    List<Reservation> reservations = Collections.emptyList();
    //    if (courtName != null) {
    //        reservations = reservationService.query(courtName);
    //    }
    //    model.addAttribute("reservations", reservations);
    //    return "reservationQuery";
    //}

    @PostMapping
    public Callable<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
        return () -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            return "reservationQuery";
        };
    }
}
