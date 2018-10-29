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
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {
    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    public ReservationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @GetMapping
    public String setupForm(@RequestParam(required = false, value = "userName") String userName, Model model) {
        Reservation reservation = new Reservation();
        reservation.setPlayer(new Player(userName, null));
        model.addAttribute("reservation", reservation);
        return "reservationForm";
    }

    @PostMapping
    public String submitForm(@ModelAttribute("reservation") @Validated Reservation reservation
        , BindingResult result
        , SessionStatus status) {

        if (result.hasErrors()) {
            return "reservationForm";
        }

        reservationService.make(reservation);
        status.setComplete();
        return "redirect:reservationSuccess";
    }

    @ModelAttribute("sportTypes")
    public List<SportType> populateSportType() {
        return reservationService.getAllSportTypes();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }

    //@ExceptionHandler(ReservationNotAvailableException.class)
    //public String handle(ReservationNotAvailableException ex) {
    //    return "reservationNotAvailable";
    //}

    //@ExceptionHandler
    //public String handleDefault(Exception ex) {
    //    return "error";
    //}
}
