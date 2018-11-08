package net.homenet.controller;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.service.ReservationService;
import net.homenet.service.ReservationValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationForm")
public class ReservationFormController {
    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public ReservationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @ModelAttribute("sportTypes")
    public Flux<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
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
    public String submitForm(@ModelAttribute("reservation") @Validated Reservation reservation, BindingResult result) {
        if (result.hasErrors())
            return "reservationForm";

        reservationService.make(reservation);
        return "redirect:reservationSuccess";
    }
}
