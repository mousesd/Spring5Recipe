package net.homenet.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PeriodicReservation {
    private String courtName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fromDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate toDate;
    private int period;
    private int hour;
    private Player player;

    public PeriodicReservation() { }

    public PeriodicReservation(String courtName, LocalDate fromDate, LocalDate toDate, int period, int hour, Player player) {
        this.courtName = courtName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.period = period;
        this.hour = hour;
        this.player = player;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
