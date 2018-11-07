package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservationForm")
public class ReservationFormController {
    private final ReservationService reservationService;

    public ReservationFormController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String setupForm(Model model) {
        Reservation reservation = new Reservation();
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
