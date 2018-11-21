package net.homenet.service;

import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.exception.ReservationNotAvailableException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SuppressWarnings("UnusedReturnValue")
public interface ReservationService {
    Flux<Reservation> query(String courtName);
    Mono<Reservation> make(Reservation reservation) throws ReservationNotAvailableException;
    Flux<SportType> getAllSportTypes();
    SportType getSportType(int sportTypeId);
    Flux<Reservation> findAll();
}
