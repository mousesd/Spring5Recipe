package net.homenet.controller;

import net.homenet.domain.Reservation;
import net.homenet.domain.ReservationQuery;
import net.homenet.service.ReservationService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

//# 1.@RequestMapping 를 이용한 방법
//@RestController
//@RequestMapping("/reservations")
//public class ReservationRestController {
//    private final ReservationService reservationService;
//
//    public ReservationRestController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }
//
//    @GetMapping
//    public Flux<Reservation> listAll() {
//        return reservationService.findAll();
//    }
//
//    @PostMapping
//    public Flux<Reservation> find(@RequestBody Mono<ReservationQuery> query) {
//        return query.flatMapMany(reservationQuery -> reservationService.query(reservationQuery.getCourtName()));
//    }
//}

//# 2.HandlerFunction<?> interface 를 이용하는 방법
//# - Tomcat 에서는 정상적으로 동작
//# - Netty 에서는 아래 예외가 발생
//#   ERROR org.springframework.web.server.adapter.HttpWebHandlerAdapter - Failed to handle request [GET http://localhost:8080/reservations]
//#       java.lang.IllegalStateException: Status and headers already sent
@Component
public class ReservationRestController {
    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    public Mono<ServerResponse> listAll(ServerRequest request) {
        return ServerResponse.ok()
            .body(reservationService.findAll(), Reservation.class);
    }

    public Mono<ServerResponse> find(ServerRequest request) {
        return ServerResponse.ok()
            .body(request.bodyToMono(ReservationQuery.class)
                .flatMapMany(o -> reservationService.query(o.getCourtName())), Reservation.class);
    }
}
