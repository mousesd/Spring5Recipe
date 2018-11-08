package net.homenet.service;

import net.homenet.domain.Player;
import net.homenet.domain.Reservation;
import net.homenet.domain.SportType;
import net.homenet.exception.ReservationNotAvailableException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

    //# Spring MVC
    //@Override
    //public List<Reservation> query(String courtName) {
    //    return this.reservations.stream()
    //        .filter(reservation -> reservation.getCourtName().equals(courtName))
    //        .collect(Collectors.toList());
    //}

    //# Spring Flux
    @Override
    public Flux<Reservation> query(String courtName) {
        return Flux.fromIterable(reservations)
            .filter(reservation -> Objects.equals(reservation.getCourtName(), courtName));
    }

    //# Spring MVC
    //@Override
    //public void make(Reservation reservation) throws ReservationNotAvailableException {
    //    long count = reservations.stream()
    //        .filter(assigned -> assigned.getCourtName().equals(reservation.getCourtName()))
    //        .filter(assigned -> assigned.getDate().equals(reservation.getDate()))
    //        .filter(assigned -> assigned.getHour() == reservation.getHour())
    //        .count();
    //
    //    if (count > 0) {
    //        throw new ReservationNotAvailableException(reservation.getCourtName()
    //            , reservation.getDate()
    //            , reservation.getHour());
    //    } else {
    //        reservations.add(reservation);
    //    }
    //}

    //# Spring Flux
    @Override
    public Mono<Reservation> make(Reservation reservation) throws ReservationNotAvailableException {
        long count = reservations.stream()
            .filter(assigned -> assigned.getCourtName().equals(reservation.getCourtName()))
            .filter(assigned -> assigned.getDate().equals(reservation.getDate()))
            .filter(assigned -> assigned.getHour() == reservation.getHour())
            .count();

        if (count > 0) {
            return Mono.error(new ReservationNotAvailableException(
                reservation.getCourtName()
                , reservation.getDate()
                , reservation.getHour()));
        } else {
            reservations.add(reservation);
            return Mono.just(reservation);
        }
    }

    //# Spring MVC
    //@Override
    //public List<SportType> getAllSportTypes() {
    //    return Arrays.asList(TENNIS, SOCCER);
    //}

    //# Spring Flux
    @Override
    public Flux<SportType> getAllSportTypes() {
        return Flux.fromIterable(Arrays.asList(TENNIS, SOCCER));
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
    public Flux<Reservation> findAll() {
        return Flux.fromIterable(reservations);
    }
}
