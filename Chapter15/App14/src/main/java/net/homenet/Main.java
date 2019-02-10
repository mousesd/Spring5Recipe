package net.homenet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext serverContext = new AnnotationConfigApplicationContext(ServerConfiguration.class);
        AnnotationConfigApplicationContext clientContext = new AnnotationConfigApplicationContext(ClientConfiguration.class);

        VacationService vacationService = clientContext.getBean(VacationService.class);
        LocalDate now = LocalDate.now();
        Date start = Date.from(now.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date stop = Date.from(now.plusDays(8).atStartOfDay(ZoneId.systemDefault()).toInstant());
        HotelReservationSearch hotelReservationSearch = new HotelReservationSearch(2, start, stop, 200f);
        List<HotelReservation> results = vacationService.findHotels(hotelReservationSearch);

        System.out.printf("Found %s results.%n", results.size());
        results.forEach(System.out::println);

        clientContext.close();
        serverContext.close();
    }
}
