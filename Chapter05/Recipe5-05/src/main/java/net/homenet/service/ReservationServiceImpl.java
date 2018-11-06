package net.homenet.service;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        reservations.add(new Reservation("Tennis #1"
            , LocalDate.of(2009, 1, 14)
            , 16
            , new Player("Roger", "N/A")
            , TENNIS));
        reservations.add(new Reservation("Tennis #1"
            , LocalDate.of(2010, 1, 14)
            , 16
            , new Player("Roger", "N/A")
            , TENNIS));
        reservations.add(new Reservation("Tennis #1"
            , LocalDate.of(2011, 1, 14)
            , 16
            , new Player("Roger", "N/A")
            , TENNIS));
        reservations.add(new Reservation("Tennis #2"
            , LocalDate.of(2008, 1, 14)
            , 20
            , new Player("James", "N/A")
            , TENNIS));
    }

    //@Override
    //public List<Reservation> query(String courtName) {
    //    return this.reservations.stream()
    //        .filter(reservation -> reservation.getCourtName().equals(courtName))
    //        .collect(Collectors.toList());
    //}

    @Override
    public Flux<Reservation> query(String courtName) {
        return Flux.fromIterable(reservations)
            .filter(reservation -> Objects.equals(reservation.getCourtName(), courtName));
    }
}
