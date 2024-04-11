package pt.ua.BusTicket.service;

import org.springframework.stereotype.Service;

import pt.ua.BusTicket.data.TripRepository;
import pt.ua.BusTicket.data.Trip;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    final TripRepository tripRepo;

    public TripService(TripRepository tripRepo) {
        this.tripRepo = tripRepo;
    }

    public Trip save(Trip trip) {
        return tripRepo.save(trip);
    }

    public List<Trip> getAllTrips() {

        return tripRepo.findAll();
    }

    public Optional<Trip> getTripDetails(Long tripId) {
        return Optional.ofNullable(tripRepo.findByTripId(tripId));
    }

    public List<Trip> getAvailableTrips(String fromPort, String toPort, String tripDate) {
        return tripRepo.findAvailableTrips(fromPort, toPort, tripDate);
    }

    public List<Trip> getAvailableTrips(String fromPort, String toPort) {
        return tripRepo.findAvailableTrips(fromPort, toPort);
    }

    public List<String> getDepartureCities() {
        return tripRepo.findDepartureCities();
    }

    public List<String> getDestinationCities() {
        return tripRepo.findDestinationCities();
    }
}