package pt.ua.BusTicket.data;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trips_table")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tripId;

    private String busCompany;
    private String dep;
    private String dest;
    private String date;
    private String time;
    private double price;

    public Trip() {
    }

    public Trip(String busCompany, String dep, String dest, String date, String time, double price) {
        this.busCompany = busCompany;
        this.dep = dep;
        this.dest = dest;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Long getTripId() {
        return this.tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getBusCompany() {
        return this.busCompany;
    }

    public void setBusCompany(String busCompany) {
        this.busCompany = busCompany;
    }

    public String getDep() {
        return this.dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getDest() {
        return this.dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Trip tripId(Long tripId) {
        setTripId(tripId);
        return this;
    }

    public Trip busCompany(String busCompany) {
        setBusCompany(busCompany);
        return this;
    }

    public Trip dep(String dep) {
        setDep(dep);
        return this;
    }

    public Trip dest(String dest) {
        setDest(dest);
        return this;
    }

    public Trip date(String date) {
        setDate(date);
        return this;
    }

    public Trip time(String time) {
        setTime(time);
        return this;
    }

    public Trip price(double price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Trip)) {
            return false;
        }
        Trip trip = (Trip) o;
        return Objects.equals(tripId, trip.tripId) && Objects.equals(busCompany, trip.busCompany) && Objects.equals(dep, trip.dep) && Objects.equals(dest, trip.dest) && Objects.equals(date, trip.date) && Objects.equals(time, trip.time) && Objects.equals(price, trip.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, busCompany, dep, dest, date, time, price);
    }

    @Override
    public String toString() {
        return "{" +
            " tripId='" + getTripId() + "'" +
            ", busCompany='" + getBusCompany() + "'" +
            ", dep='" + getDep() + "'" +
            ", dest='" + getDest() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }


}