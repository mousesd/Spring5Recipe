package app03

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

class ReservationSpec extends Specification implements DomainUnitTest<Reservation> {
    def setup() {
    }

    def cleanup() {
    }

    void testReservation() {
        given:
            def calendar = LocalDateTime.of(2017, 10, 13, 15, 00).atZone(ZoneId.systemDefault()).toInstant()
            def validDateReservation = Date.from(calendar)
            def reservation = new Reservation(sportType: 'Tennis'
                , courtName: 'Main'
                , date: validDateReservation
                , player: new Player(name: 'James', phone: '120-111'))
        expect:
            reservation.validate()
    }

    void testOutOfRangeDateReservation() {
        given:
            def calendar = LocalDateTime.of(2017, 10, 13, 23, 00).atZone(ZoneId.systemDefault()).toInstant()
            def invalidDateReservation = Date.from(calendar)
            def reservation = new Reservation(sportType: 'Tennis'
                , courtName: 'Main'
                , date: invalidDateReservation
                , player: new Player(name: 'James', phone: '120-111'))

        expect:
            !reservation.validate()
            reservation.errors['date'].code == 'invalid.weekdayHour'
    }

    void testOutOfRangeSportTypeReservation() {
        given:
            def calendar = LocalDateTime.of(2017, 10, 13, 15, 00).toInstant(ZoneOffset.UTC)
            def validDateReservation = Date.from(calendar)
            def reservation = new Reservation(sportType: 'Baseball'
                , courtName: 'Main'
                , date: validDateReservation
                , player: new Player(name: 'James', phone: '120-111'))
        expect:
            !reservation.validate()
            reservation.errors['sportType'].code.contains('not.inList')
    }
}
