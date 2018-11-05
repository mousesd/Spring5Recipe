package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.service.ReservationService;
import net.homenet.util.Delayer;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    @GetMapping
    public void setupForm() { }

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

    @GetMapping(params = "courtName")
    public ResponseBodyEmitter find(@RequestParam("courtName") String courtName) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        taskExecutor.execute(() -> {
            List<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    emitter.send(reservation);
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
