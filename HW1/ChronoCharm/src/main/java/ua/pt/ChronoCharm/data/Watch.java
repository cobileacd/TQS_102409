package ua.pt.ChronoCharm.data;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "watches_table")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long watchId;

    private String brand;
    private String model;
    private String refNumber;

    private String movementType;
    private String caseDiameter;

    private Integer release_year;
    private Integer price;

    public Watch(String brand, String model, String refNumber, String movementType, String caseDiameter, Integer release_year, Integer price) {
        this.brand = brand;
        this.model = model;
        this.refNumber = refNumber;
        this.movementType = movementType;
        this.caseDiameter = caseDiameter;
        this.release_year = release_year;
        this.price = price;
    }

    public Long getWatchId() {
        return this.watchId;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRefNumber() {
        return this.refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getMovementType() {
        return this.movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getCaseDiameter() {
        return this.caseDiameter;
    }

    public void setCaseDiameter(String caseDiameter) {
        this.caseDiameter = caseDiameter;
    }

    public Integer getRelease_year() {
        return this.release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Watch brand(String brand) {
        setBrand(brand);
        return this;
    }

    public Watch model(String model) {
        setModel(model);
        return this;
    }

    public Watch refNumber(String refNumber) {
        setRefNumber(refNumber);
        return this;
    }

    public Watch movementType(String movementType) {
        setMovementType(movementType);
        return this;
    }

    public Watch caseDiameter(String caseDiameter) {
        setCaseDiameter(caseDiameter);
        return this;
    }

    public Watch release_year(Integer release_year) {
        setRelease_year(release_year);
        return this;
    }

    public Watch price(Integer price) {
        setPrice(price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Watch)) {
            return false;
        }
        Watch watch = (Watch) o;
        return Objects.equals(brand, watch.brand) && Objects.equals(model, watch.model) && Objects.equals(refNumber, watch.refNumber) && Objects.equals(movementType, watch.movementType) && Objects.equals(caseDiameter, watch.caseDiameter) && Objects.equals(release_year, watch.release_year) && Objects.equals(price, watch.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, refNumber, movementType, caseDiameter, release_year, price);
    }

    @Override
    public String toString() {
        return "{" +
            " brand='" + getBrand() + "'" +
            ", model='" + getModel() + "'" +
            ", refNumber='" + getRefNumber() + "'" +
            ", movementType='" + getMovementType() + "'" +
            ", caseDiameter='" + getCaseDiameter() + "'" +
            ", release_year='" + getRelease_year() + "'" +
            ", price='" + getPrice() + "'" +
            "}";
    }

    public Watch() {
    }
}