package pt.ua.BusTicket.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import pt.ua.BusTicket.service.TripService;
import pt.ua.BusTicket.data.Trip;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "api/trips")
public class TripController {

    private static Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private TripService tripService;

    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable int tripId) {
        logger.debug("Trip with id:{} requested", tripId);

        Optional<Trip> tripOptional = tripService.getTripDetails((long) tripId);

        if (tripOptional.isPresent()) {
            Trip trip = tripOptional.get();
            return ResponseEntity.ok(trip);
        } else {
            logger.warn("Trip with id:{} requested not found", tripId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{departureCity}/{destinationCity}")
    public ResponseEntity<List<Trip>> getTripsFromTo(@PathVariable String departureCity, 
                                               @PathVariable String destinationCity) {
        logger.debug("Trips from {} to {} were requested", departureCity, destinationCity);

        List<Trip> availableTrips = tripService.getAvailableTrips(departureCity, destinationCity);

        return ResponseEntity.ok(availableTrips);
    }

}