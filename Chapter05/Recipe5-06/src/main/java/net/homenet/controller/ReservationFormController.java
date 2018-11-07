package net.homenet.controller;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationForm")
public class ReservationFormController {
    private final ReservationService reservationService;

    public ReservationFormController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @ModelAttribute("sportTypes")
    public Flux<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    @GetMapping
    public String setupForm(@RequestParam(value = "userName", required = false) String userName, Model model) {
        Player player = new Player(userName, null);
        Reservation reservation = new Reservation();
        reservation.setPlayer(player);
        model.addAttribute("reservation", reservation);
        return "reservationForm";
    }

    @SuppressWarnings("UnassignedFluxMonoInstance")
    @PostMapping
    public String submitForm(@ModelAttribute("reservation") Reservation reservation, BindingResult result) {
        reservationService.make(reservation);
        return "redirect:reservationSuccess";
    }
}
