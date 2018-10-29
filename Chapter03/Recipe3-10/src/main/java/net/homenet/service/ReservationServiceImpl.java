package net.homenet.service;

import net.homenet.domain.PeriodicReservation;
import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final SportType TENNIS = new SportType(1, "Tennis");
    private static final SportType SOCCER = new SportType(2, "Soccer");

    private final List<Reservation> reservations = new ArrayList<>();

    public ReservationServiceImpl() {
        reservations.add(new Reservation("Tennis #1"
            , LocalDate.of(2008, 1, 14)
            , 16
            , new Player("Roger", "N/A")
            , TENNIS));
        reservations.add(new Reservation("Tennis #2"
            , LocalDate.of(2008, 1, 14)
            , 20
            , new Player("James", "N/A")
            , TENNIS));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations.stream()
            .filter(reservation -> reservation.getCourtName().equals(courtName))
            .collect(Collectors.toList());
    }

    @Override
    public void make(Reservation reservation) throws ReservationNotAvailableException {
        long count = reservations.stream()
            .filter(assigned -> assigned.getCourtName().equals(reservation.getCourtName()))
            .filter(assigned -> assigned.getDate().equals(reservation.getDate()))
            .filter(assigned -> assigned.getHour() == reservation.getHour())
            .count();

        if (count > 0) {
            throw new ReservationNotAvailableException(reservation.getCourtName()
                , reservation.getDate()
                , reservation.getHour());
        } else {
            reservations.add(reservation);
        }
    }

    @Override
    public List<SportType> getAllSportTypes() {
        return Arrays.asList(TENNIS, SOCCER);
    }

    @Override
    public SportType getSportType(int sportTypeId) {
        switch (sportTypeId) {
            case 1:
                return TENNIS;
            case 2:
                return SOCCER;
            default:
                return null;
        }
    }

    @Override
    public void makePeriodic(PeriodicReservation periodicReservation) throws ReservationNotAvailableException {
        LocalDate fromDate = periodicReservation.getFromDate();
        while (fromDate.isBefore(periodicReservation.getToDate())) {
            Reservation reservation = new Reservation();
            reservation.setCourtName(periodicReservation.getCountName());
            reservation.setDate(fromDate);
            reservation.setHour(periodicReservation.getHour());
            reservation.setPlayer(periodicReservation.getPlayer());
            make(reservation);
            fromDate = fromDate.plusDays(periodicReservation.getPeriod());
        }
    }
}
