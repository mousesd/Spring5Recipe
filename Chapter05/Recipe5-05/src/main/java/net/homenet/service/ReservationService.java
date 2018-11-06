package net.homenet.service;

import net.homenet.domain.Reservation;
import reactor.core.publisher.Flux;

public interface ReservationService {
    Flux<Reservation> query(String courtName);
}
