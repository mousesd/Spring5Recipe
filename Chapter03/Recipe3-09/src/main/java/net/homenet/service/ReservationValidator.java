package net.homenet.service;

import net.homenet.domain.Reservation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
public class ReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Reservation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName", "required.courtName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "required.date");
        ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name", "required.playerName");
        ValidationUtils.rejectIfEmpty(errors, "sportType", "required.sportType");

        Reservation reservation = (Reservation) target;
        LocalDate date = reservation.getDate();
        int hour = reservation.getHour();
        if (date != null) {
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                if (hour < 8 || hour > 22) {
                    errors.reject("invalid.holidayHour");
                }
            } else {
                if (hour < 9 || hour > 21) {
                    errors.reject("invalid.weekdayHour");
                }
            }
        }
    }
}
