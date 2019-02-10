package net.homenet;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@SuppressWarnings({ "unused", "WeakerAccess" })
public class HotelReservation implements Serializable, Comparable<HotelReservation> {
    private static final long serialVersionUID = 1L;

    private String id;
    private String hotelName;
    private Float price;

    public HotelReservation() { }

    public HotelReservation(String hotelName, Float price) {
        this.id = UUID.randomUUID().toString();
        this.hotelName = hotelName;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HotelReservation [" +
            "id='" + id + '\'' +
            ", hotelName='" + hotelName + '\'' +
            ", price=" + price +
            ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HotelReservation that = (HotelReservation) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(HotelReservation o) {
        return id.compareTo(o.id);
    }
}
