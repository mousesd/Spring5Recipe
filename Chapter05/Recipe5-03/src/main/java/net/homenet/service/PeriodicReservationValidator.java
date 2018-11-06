package net.homenet.service;

import net.homenet.domain.PeriodicReservation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PeriodicReservationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PeriodicReservation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validateCourt(errors);
        validateTime(errors);
        validatePlayer(errors);
    }

    public void validatePlayer(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "player.name", "required.playerName");
    }

    public void validateTime(Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "fromDate", "required.fromDate");
        ValidationUtils.rejectIfEmpty(errors, "toDate", "required.toDate");
        ValidationUtils.rejectIfEmpty(errors, "period", "required.period");
        ValidationUtils.rejectIfEmpty(errors, "hour", "required.hour");
    }

    public void validateCourt(Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courtName", "required.courtName");
    }
}
