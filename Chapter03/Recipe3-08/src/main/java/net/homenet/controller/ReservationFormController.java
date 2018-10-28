package net.homenet.controller;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/reservationForm")
public class ReservationFormController {
    private final ReservationService reservationService;

    public ReservationFormController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String setupForm(@RequestParam(required = false, value = "userName") String userName, Model model) {
        Reservation reservation = new Reservation();
        reservation.setPlayer(new Player(userName, null));
        model.addAttribute("reservation", reservation);
        return "reservationForm";
    }

    @PostMapping
    public String submitForm(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.make(reservation);
        return "redirect:reservationSuccess";
    }

    @ModelAttribute("sportTypes")
    public List<SportType> populateSportType() {
        return reservationService.getAllSportTypes();
    }
}
