package net.homenet.s1.service;

import net.homenet.s1.domain.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
}
