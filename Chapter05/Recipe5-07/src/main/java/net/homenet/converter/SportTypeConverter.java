package net.homenet.converter;

import net.homenet.domain.SportType;
import net.homenet.service.ReservationService;
import org.springframework.core.convert.converter.Converter;

public class SportTypeConverter implements Converter<String, SportType> {
    private final ReservationService reservationService;

    public SportTypeConverter(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public SportType convert(String source) {
        int sportTypeId = Integer.valueOf(source);
        return reservationService.getSportType(sportTypeId);
    }
}
