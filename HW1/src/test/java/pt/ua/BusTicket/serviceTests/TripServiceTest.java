package pt.ua.BusTicket.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusTicket.service.TripService;
import pt.ua.BusTicket.data.Trip;
import pt.ua.BusTicket.data.TripRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TripServiceTest{

    // prevent strict stubbing
    @Mock( lenient = true )
    private TripRepository tripRepository;

    @InjectMocks
    private TripService tripService;

    @BeforeEach
    public void setUp() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);
        Trip trip3 = new Trip("Yet another Bus Company", "New York", "Chicago", "2024-04-13", "12:10", 200.0);

        List<String> departureCities = Arrays.asList("Chicago", "Miami", "New York");
        List<String> destinationCities = Arrays.asList("Chicago", "Miami", "Texas");

        Mockito.when(tripRepository.findByTripId(1L)).thenReturn(trip0);
        Mockito.when(tripRepository.findByTripId(2L)).thenReturn(trip1);
        Mockito.when(tripRepository.findByTripId(3L)).thenReturn(trip2);
        Mockito.when(tripRepository.findByTripId(4L)).thenReturn(trip3);
        Mockito.when(tripRepository.findDepartureCities()).thenReturn(departureCities);
        Mockito.when(tripRepository.findDestinationCities()).thenReturn(destinationCities);
        Mockito.when(tripRepository.findAvailableTrips("Chicago", "Miami", "2024-04-11")).thenReturn(Arrays.asList(trip0, trip1));
        Mockito.when(tripRepository.findAvailableTrips("Chicago", "Miami")).thenReturn(Arrays.asList(trip0, trip1, trip2));
        Mockito.when(tripRepository.findAll()).thenReturn(Arrays.asList(trip0, trip1, trip2, trip3));
    }

    @Test
    void whenSearchForValidId_thenTripShouldBeFound() {
        Trip found = tripService.getTripDetails(1L).get();
        assertThat(found.getBusCompany()).isEqualTo("Some Bus Company");
    }

    @Test
    void whenSearchInvalidId_thenTripShouldNotBeFound() {
        assertThat(tripService.getTripDetails(5L).isPresent()).isFalse();

        Mockito.verify(tripRepository, Mockito.times(1)).findByTripId(Mockito.anyLong());
    }

    @Test
    void whenGetDepartureCities_thenReturnDepartureCities() {
        List<String> departureCities = Arrays.asList("Chicago", "Miami", "New York");
        List<String> _departureCities = tripService.getDepartureCities();
        assertThat(_departureCities).isEqualTo(departureCities);

        Mockito.verify(tripRepository, Mockito.times(1)).findDepartureCities();
    }

    @Test
    void whenGetDestinationCities_thenReturnDestinationCities() {
        List<String> destinationCities = Arrays.asList("Chicago", "Miami", "Texas");
        List<String> _destinationCities = tripService.getDestinationCities();
        assertThat(_destinationCities).isEqualTo(destinationCities);

        Mockito.verify(tripRepository, Mockito.times(1)).findDestinationCities();
    }

    @Test
    void whenGetAllTrips_thenReturnAllTrips() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);
        Trip trip3 = new Trip("Yet another Bus Company", "New York", "Chicago", "2024-04-13", "12:10", 200.0);

        List<Trip> allTrips = tripService.getAllTrips();

        assertThat(allTrips).hasSize(4).extracting(Trip::getTripId).contains(trip0.getTripId(), trip1.getTripId(), trip2.getTripId(), trip3.getTripId());

        Mockito.verify(tripRepository, Mockito.times(1)).findAll();
    }

    @Test
    void whenFindAllTripsFromToByDate_thenReturnAllTripsFromToByDate() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);

        List<Trip> allTrips = tripService.getAvailableTrips("Chicago", "Miami", "2024-04-11");

        assertThat(allTrips).hasSize(2).extracting(Trip::getTripId).contains(trip0.getTripId(), trip1.getTripId());

        Mockito.verify(tripRepository, Mockito.times(1)).findAvailableTrips("Chicago", "Miami", "2024-04-11");
    }

    @Test
    void whenFindAllTripsFromTo_thenReturnAllTripsFromTo() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        List<Trip> allTrips = tripService.getAvailableTrips("Chicago", "Miami");

        assertThat(allTrips).hasSize(3).extracting(Trip::getTripId).contains(trip0.getTripId(), trip1.getTripId(), trip2.getTripId());

        Mockito.verify(tripRepository, Mockito.times(1)).findAvailableTrips("Chicago", "Miami");
    }

}