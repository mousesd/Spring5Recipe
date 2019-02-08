package net.homenet;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings({ "unused", "WeakerAccess" })
public class HotelReservationSearch implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int roomsDesired;
    private final Date start;
    private final Date stop;
    private final float maximumPrice;

    public HotelReservationSearch() {
        this(0, null, null, 0);
    }

    public HotelReservationSearch(int roomsDesired, Date start, Date stop, float maximumPrice) {
        this.roomsDesired = roomsDesired;
        this.start = start;
        this.stop = stop;
        this.maximumPrice = maximumPrice;
    }

    public int getRoomsDesired() {
        return roomsDesired;
    }

    public Date getStart() {
        return start;
    }

    public Date getStop() {
        return stop;
    }

    public float getMaximumPrice() {
        return maximumPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HotelReservationSearch that = (HotelReservationSearch) obj;
        return roomsDesired == that.roomsDesired
            && Float.compare(maximumPrice, that.maximumPrice) == 0
            && Objects.equals(start, that.start)
            && Objects.equals(stop, that.stop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomsDesired, start, stop, maximumPrice);
    }

    @Override
    public String toString() {
        return "HotelReservationSearch [" +
            "roomsDesired=" + roomsDesired +
            ", start=" + start +
            ", stop=" + stop +
            ", maximumPrice=" + maximumPrice +
            ']';
    }
}
