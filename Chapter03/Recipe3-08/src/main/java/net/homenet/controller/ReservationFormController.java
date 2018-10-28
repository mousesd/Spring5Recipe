package net.homenet.controller;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        //# Date, SportType 이 jsp 페이지에서 설정되지 않아 하드코딩
        reservation.setDate(LocalDate.of(2018, 10, 29));
        reservation.setSportType(new SportType(1, "Tennis"));
        reservationService.make(reservation);
        return "reservationSuccess";
    }

    @ModelAttribute("sportTypes")
    public List<SportType> populateSportType() {
        return reservationService.getAllSportTypes();
    }
}
