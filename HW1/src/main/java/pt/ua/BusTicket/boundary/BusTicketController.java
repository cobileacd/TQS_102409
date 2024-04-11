package pt.ua.BusTicket.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import pt.ua.BusTicket.Generated;

import pt.ua.BusTicket.data.Trip;
import pt.ua.BusTicket.service.TripService;
import pt.ua.BusTicket.data.Ticket;
import pt.ua.BusTicket.service.TicketService;
import pt.ua.BusTicket.service.CurrencyService;

import java.util.List;
import java.util.Optional;

// This is covered somewhat by the selenium test.
@Generated
@Controller
public class BusTicketController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("departureCities", tripService.getDepartureCities());
        model.addAttribute("destinationCities", tripService.getDestinationCities());
        model.addAttribute("currencyCodes", currencyService.getCurrencyCodes());

        return "index"; //view
    }

    @PostMapping("/reserve")
    public String getAvailableTrips(@RequestParam("fromPort") String fromPort,
                                    @RequestParam("toPort") String toPort,
                                    @RequestParam("tripDate") String tripDate,
                                    @RequestParam("currency") String currency,
                                    Model model) {

        List<Trip> availableTrips = tripService.getAvailableTrips(fromPort, toPort, tripDate);

        double rate = currencyService.GetExchangeRateFromDollar(currency);
        for (Trip trip : availableTrips)
        {
            trip.setPrice(trip.getPrice() * rate);
        }

        model.addAttribute("currencyCode", currency);
        model.addAttribute("availableTrips", availableTrips);

        return "reserve";
    }

    @GetMapping("/purchase")
    public String showUserInfoPurchasePage(@RequestParam("tripId") Long tripId, @RequestParam("cur") String currencyCode, Model model) {
        Optional<Trip> tripOptional = tripService.getTripDetails((long) tripId);

        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();

            double rate = currencyService.GetExchangeRateFromDollar(currencyCode);
            trip.setPrice(trip.getPrice() * rate);

            model.addAttribute("trip", trip);
            model.addAttribute("currency", currencyCode);
            return "purchase"; 
        } else {
            throw new IllegalArgumentException("Trip with ID " + tripId + " not found"); 
        }
    }

    @PostMapping("/purchase")
    public String purchaseTicket(@RequestParam("tripId") Long tripId,
                                 @RequestParam("name") String name,
                                 @RequestParam("address") String address,
                                 @RequestParam("city") String city,
                                 @RequestParam("state") String state,
                                 @RequestParam("zipCode") String zipCode,
                                 @RequestParam("cardType") String cardType,
                                 @RequestParam("cardNumber") String cardNumber,
                                 @RequestParam("month") String month,
                                 @RequestParam("year") String year,
                                 @RequestParam("nameOnCard") String nameOnCard,
                                 Model model) {

        Ticket ticket = new Ticket();
        ticket.setTripId(tripId);
        ticket.setName(name);
        ticket.setAddress(address);
        ticket.setCity(city);
        ticket.setState(state);
        ticket.setZipCode(zipCode);
        ticket.setCardType(cardType);
        ticket.setCardNumber(cardNumber);
        ticket.setDateMonth(month);
        ticket.setDateYear(year);
        ticket.setNameOnCard(nameOnCard);

        ticketService.save(ticket);

        return "confirmation";
    }

}