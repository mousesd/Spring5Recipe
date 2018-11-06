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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
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

    //# POST http://localhost:8080/reservationQuery?courtName=Tennis #1
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

    //# 1.GET http://localhost:8080/reservationQuery?courtName=Tennis #1
    //# - 예외 발생!
    //  org.apache.catalina.connector.CoyoteAdapter.checkRecycled Encountered a non-recycled request and recycled it forcedly.
    //  org.apache.catalina.connector.CoyoteAdapter$RecycleRequiredException
    //  at org.apache.catalina.connector.CoyoteAdapter.checkRecycled(CoyoteAdapter.java:501)
    //  at org.apache.coyote.http11.Http11Processor.recycle(Http11Processor.java:1706)
    //  at org.apache.coyote.AbstractProtocol$ConnectionHandler.release(AbstractProtocol.java:988)
    //  at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:922)
    //  at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1498)
    //  at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    //  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
    //  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
    //  at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    //  at java.lang.Thread.run(Thread.java:748)
    //@GetMapping(params = "courtName")
    //public ResponseBodyEmitter find(@RequestParam("courtName") String courtName) {
    //    final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    //    taskExecutor.execute(() -> {
    //        Collection<Reservation> reservations = reservationService.query(courtName);
    //        try {
    //            for (Reservation reservation : reservations) {
    //                emitter.send(reservation);
    //            }
    //            emitter.complete();
    //        } catch (IOException e) {
    //            emitter.completeWithError(e);
    //        }
    //    });
    //    return emitter;
    //}

    //# 2.
    //@GetMapping(params = "courtName")
    //@ResponseBody
    //public ResponseEntity<ResponseBodyEmitter> find(@RequestParam("courtName") String courtName) {
    //    ResponseBodyEmitter emitter = new ResponseBodyEmitter();
    //    taskExecutor.execute(() -> {
    //        Collection<Reservation> reservations = reservationService.query(courtName);
    //        try {
    //            for (Reservation reservation : reservations) {
    //                emitter.send(reservation);
    //            }
    //            emitter.complete();
    //        } catch (IOException e) {
    //            emitter.completeWithError(e);
    //        }
    //    });
    //
    //    return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
    //        .header("Custom-Header", "Custom-Value")
    //        .body(emitter);
    //}

    //# 3.
    //@GetMapping(params = "courtName")
    //public SseEmitter find(@RequestParam("courtName") String courtName) {
    //    SseEmitter emitter = new SseEmitter();
    //    taskExecutor.execute(() -> {
    //        Collection<Reservation> reservations = reservationService.query(courtName);
    //        try {
    //            for (Reservation reservation : reservations) {
    //                Delayer.delay(125);
    //                emitter.send(reservation);
    //            }
    //            emitter.complete();
    //        } catch (IOException e) {
    //            emitter.completeWithError(e);
    //        }
    //    });
    //    return emitter;
    //}

    //# 4.
    @GetMapping(params = "courtName")
    public SseEmitter find(@RequestParam("courtName") String courtName) {
        SseEmitter emitter = new SseEmitter();
        taskExecutor.execute(() -> {
            Collection<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    Delayer.delay(300);
                    emitter.send(SseEmitter.event()
                        .id(String.valueOf(reservation.hashCode()))
                        .data(reservation));
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
