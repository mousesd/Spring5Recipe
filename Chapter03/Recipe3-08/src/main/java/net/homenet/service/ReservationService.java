package net.homenet.service;

import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
    void make(Reservation reservation) throws ReservationNotAvailableException;
    List<SportType> getAllSportTypes();
}
