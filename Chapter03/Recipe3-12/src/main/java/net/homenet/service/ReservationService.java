package net.homenet.service;

import net.homenet.domain.PeriodicReservation;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.exception.ReservationNotAvailableException;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> query(String courtName);
    void make(Reservation reservation) throws ReservationNotAvailableException;
    List<SportType> getAllSportTypes();
    SportType getSportType(int sportTypeId);
    void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException;
    List<Reservation> findByDate(LocalDate date);
}
