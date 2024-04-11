package pt.ua.BusTicket.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.ua.BusTicket.service.TripService;
import pt.ua.BusTicket.data.Trip;
import pt.ua.BusTicket.service.TicketService;
import pt.ua.BusTicket.data.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/reservations")
public class TicketController {

    private static Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TripService tripService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable int ticketId) {
        logger.debug("Ticket with id:{} requested", ticketId);

        Optional<Ticket> ticketOptional = ticketService.getTicketDetails((long) ticketId);

        if (ticketOptional.isPresent()) {
            Ticket ticket = ticketOptional.get();
            return ResponseEntity.ok(ticket);
        } else {
            logger.warn("Ticket with id:{} requested not found", ticketId);
            return ResponseEntity.notFound().build();
        }
    }

/*
    // Form-data
    @PostMapping("/purchase")
    public ResponseEntity<String> reserveTicket(@RequestParam("tripId") Long tripId,
                                                @RequestParam("name") String name,
                                                @RequestParam("address") String address,
                                                @RequestParam("city") String city,
                                                @RequestParam("state") String state,
                                                @RequestParam("zipCode") String zipCode,
                                                @RequestParam("cardType") String cardType,
                                                @RequestParam("cardNumber") String cardNumber,
                                                @RequestParam("month") String month,
                                                @RequestParam("year") String year,
                                                @RequestParam("nameOnCard") String nameOnCard ) {

        if (!tripService.getTripDetails(tripId).isPresent()) {
            return ResponseEntity.ok("an error occured when trying to reserve a ticket");
        }

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

        return ResponseEntity.ok("success");
    }
    */

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> reserveTicket(@RequestBody Ticket ticket) {
        // Check if tripId exists
        if (!tripService.getTripDetails(ticket.getTripId()).isPresent()) {
            return ResponseEntity.ok(null);
        }

        ticketService.save(ticket);

        return ResponseEntity.ok(ticket);
    }

}