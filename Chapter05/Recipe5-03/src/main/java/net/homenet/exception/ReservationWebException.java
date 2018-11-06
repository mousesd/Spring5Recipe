package net.homenet.exception;

import java.util.Date;

public class ReservationWebException extends RuntimeException {
    private String message;
    private Date date;
    private String stack;

    public ReservationWebException(String message, Date date, String stack) {
        this.message = message;
        this.date = date;
        this.stack = stack;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getStack() {
        return stack;
    }
}
