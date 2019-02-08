package net.homenet;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.List;

@MessagingGateway
public interface VacationService {
    @Gateway
    List<HotelReservation> findHotels(HotelReservationSearch hotelReservationSearch);
}
