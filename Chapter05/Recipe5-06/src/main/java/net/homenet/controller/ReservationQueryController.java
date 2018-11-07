package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public void setupForm() { }

    @PostMapping
    public String submitForm(ServerWebExchange exchange, Model model) {
        Flux<Reservation> reservations = exchange.getFormData()
            .map(form -> form.get("courtName"))
            .flatMapMany(Flux::fromIterable)
            .concatMap(reservationService::query);
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }
}
