package net.homenet.service;

import net.homenet.domain.Reservation;
import net.homenet.exception.ReservationNotAvailableException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReservationService {
    Flux<Reservation> query(String courtName);
    Mono<Reservation> make(Reservation reservation) throws ReservationNotAvailableException;
}
