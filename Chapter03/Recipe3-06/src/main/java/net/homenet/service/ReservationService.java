package net.homenet.service;

import net.homenet.domain.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
}
