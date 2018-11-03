package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.service.ReservationService;
import net.homenet.util.Delayer;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationQueryController {
    private final ReservationService reservationService;
    private final TaskExecutor taskExecutor;

    public ReservationQueryController(ReservationService reservationService, TaskExecutor taskExecutor) {
        this.reservationService = reservationService;
        this.taskExecutor = taskExecutor;
    }

    //@GetMapping
    //public String setupForm(Model model) {
    //    model.addAttribute("courtName", "");
    //    model.addAttribute("reservations", new ArrayList<Reservation>());
    //    return "reservationQuery";
    //}

    @GetMapping
    public void setupForm() { }

    //# 1.Sync
    //@PostMapping
    //public String submitForm(@RequestParam("courtName") String courtName, Model model) {
    //    List<Reservation> reservations = Collections.emptyList();
    //    if (courtName != null) {
    //        reservations = reservationService.query(courtName);
    //    }
    //    model.addAttribute("reservations", reservations);
    //    return "reservationQuery";
    //}

    //# 2.Async #1) Callable<?>
    //@PostMapping
    //public Callable<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
    //    return () -> {
    //        List<Reservation> reservations = Collections.emptyList();
    //        if (courtName != null) {
    //            Delayer.randomDelay();
    //            reservations = reservationService.query(courtName);
    //        }
    //        model.addAttribute("reservations", reservations);
    //        return "reservationQuery";
    //    };
    //}

    //# 3.Async #2) DeferredResult<?>
    //@PostMapping
    //public DeferredResult<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
    //    DeferredResult<String> result = new DeferredResult<>();
    //    taskExecutor.execute(() -> {
    //        List<Reservation> reservations = Collections.emptyList();
    //        if (courtName != null) {
    //            Delayer.randomDelay();
    //            reservations = reservationService.query(courtName);
    //        }
    //        model.addAttribute("reservations", reservations);
    //        result.setResult("reservationQuery");
    //    });
    //    return result;
    //}

    //# 4.Async #3) CompletableFuture<?>
    @PostMapping
    public CompletableFuture<String> submitForm(@RequestParam("courtName") String courtName, Model model) {
        return CompletableFuture.supplyAsync(() -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                Delayer.randomDelay();
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            return "reservationQuery";
        }, taskExecutor);
    }
}
