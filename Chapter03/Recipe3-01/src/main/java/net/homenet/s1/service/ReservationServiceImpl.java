package net.homenet.s1.service;

import net.homenet.s1.domain.Player;
import net.homenet.s1.domain.Reservation;
import net.homenet.s1.domain.SportType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
            , new Player("Reger", "N/A")
            , TENNIS));
        reservations.add(new Reservation("Tennis #2"
            , LocalDate.of(2008, 1, 14)
            , 20
            , new Player("James", "N/A")
            , TENNIS));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return reservations.stream()
            .filter(reservation -> reservation.getCourtName().equals(courtName))
            .collect(Collectors.toList());
    }
}
