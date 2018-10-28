package net.homenet.service;

import java.time.LocalDate;
import java.util.Date;

public class ReservationNotAvailableException extends RuntimeException {
    private final String courtName;
    private final LocalDate date;
    private final int hour;

    public ReservationNotAvailableException(String courtName, LocalDate date, int hour) {
        this.courtName = courtName;
        this.date = date;
        this.hour = hour;
    }

    public String getCourtName() {
        return courtName;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }
}
