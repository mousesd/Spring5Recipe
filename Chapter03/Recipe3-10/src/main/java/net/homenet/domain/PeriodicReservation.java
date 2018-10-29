package net.homenet.domain;

import java.time.LocalDate;
import java.util.Date;

public class PeriodicReservation {
    private String countName;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int period;
    private int hour;
    private Player player;

    public PeriodicReservation() { }

    public PeriodicReservation(String countName, LocalDate fromDate, LocalDate toDate, int period, int hour, Player player) {
        this.countName = countName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.period = period;
        this.hour = hour;
        this.player = player;
    }

    public String getCountName() {
        return countName;
    }

    public void setCountName(String countName) {
        this.countName = countName;
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
