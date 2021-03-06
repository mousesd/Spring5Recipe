package net.homenet;

import org.springframework.integration.annotation.ServiceActivator;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

public class VacationServiceImpl implements VacationService {
    private List<HotelReservation> hotelReservations;

    @PostConstruct
    public void afterPropertiesSet() {
        hotelReservations = Arrays.asList(new HotelReservation("Bilton", 243.200F)
            , new HotelReservation("East Western", 75.0F)
            , new HotelReservation("Thairfield Inn", 70F)
            , new HotelReservation("Park In The Inn", 200.00F));
    }

    @Override
    @ServiceActivator
    public List<HotelReservation> findHotels(HotelReservationSearch hotelReservationSearch) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hotelReservations;
    }
}
