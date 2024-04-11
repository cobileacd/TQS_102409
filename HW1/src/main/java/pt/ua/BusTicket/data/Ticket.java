package pt.ua.BusTicket.data;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tickets_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    private Long tripId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String cardType;
    private String cardNumber;
    private String dateMonth;
    private String dateYear;
    private String nameOnCard;

    public Ticket() {
    }

    public Ticket(Long tripId, String name, String address, String city, String state, String zipCode, String cardType, String cardNumber, String dateMonth, String dateYear, String nameOnCard) {
        this.tripId = tripId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.dateMonth = dateMonth;
        this.dateYear = dateYear;
        this.nameOnCard = nameOnCard;
    }

    public Long getTicketId() {
        return this.ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTripId() {
        return this.tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDateMonth() {
        return this.dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateYear() {
        return this.dateYear;
    }

    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }

    public String getNameOnCard() {
        return this.nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public Ticket ticketId(Long ticketId) {
        setTicketId(ticketId);
        return this;
    }

    public Ticket tripId(Long tripId) {
        setTripId(tripId);
        return this;
    }

    public Ticket name(String name) {
        setName(name);
        return this;
    }

    public Ticket address(String address) {
        setAddress(address);
        return this;
    }

    public Ticket city(String city) {
        setCity(city);
        return this;
    }

    public Ticket state(String state) {
        setState(state);
        return this;
    }

    public Ticket zipCode(String zipCode) {
        setZipCode(zipCode);
        return this;
    }

    public Ticket cardType(String cardType) {
        setCardType(cardType);
        return this;
    }

    public Ticket cardNumber(String cardNumber) {
        setCardNumber(cardNumber);
        return this;
    }

    public Ticket dateMonth(String dateMonth) {
        setDateMonth(dateMonth);
        return this;
    }

    public Ticket dateYear(String dateYear) {
        setDateYear(dateYear);
        return this;
    }

    public Ticket nameOnCard(String nameOnCard) {
        setNameOnCard(nameOnCard);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(ticketId, ticket.ticketId) && Objects.equals(tripId, ticket.tripId) && Objects.equals(name, ticket.name) && Objects.equals(address, ticket.address) && Objects.equals(city, ticket.city) && Objects.equals(state, ticket.state) && Objects.equals(zipCode, ticket.zipCode) && Objects.equals(cardType, ticket.cardType) && Objects.equals(cardNumber, ticket.cardNumber) && Objects.equals(dateMonth, ticket.dateMonth) && Objects.equals(dateYear, ticket.dateYear) && Objects.equals(nameOnCard, ticket.nameOnCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, tripId, name, address, city, state, zipCode, cardType, cardNumber, dateMonth, dateYear, nameOnCard);
    }

    @Override
    public String toString() {
        return "{" +
            " ticketId='" + getTicketId() + "'" +
            ", tripId='" + getTripId() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", cardType='" + getCardType() + "'" +
            ", cardNumber='" + getCardNumber() + "'" +
            ", dateMonth='" + getDateMonth() + "'" +
            ", dateYear='" + getDateYear() + "'" +
            ", nameOnCard='" + getNameOnCard() + "'" +
            "}";
    }

}