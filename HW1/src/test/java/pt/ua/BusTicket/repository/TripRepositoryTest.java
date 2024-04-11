package pt.ua.BusTicket.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.BusTicket.data.TripRepository;
import pt.ua.BusTicket.data.Trip;

import java.util.List;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
public class TripRepositoryTest {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @Test
    void whenFindTripByExistingId_thenReturnTrip() {
        Trip trip = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        entityManager.persistAndFlush(trip);

        Trip fromDb = tripRepository.findByTripId(trip.getTripId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getBusCompany()).isEqualTo(trip.getBusCompany());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Trip fromDb = tripRepository.findByTripId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfTrips_whenFindAll_thenReturnAllTrips() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        entityManager.persist(trip0);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        List<Trip> allTrips = tripRepository.findAll();

        assertThat(allTrips).hasSize(3).extracting(Trip::getBusCompany).containsOnly(trip0.getBusCompany(), trip1.getBusCompany(), trip2.getBusCompany());
    }

    @Test
    void givenSetOfTrips_whenFindFromToByDate_thenReturnAllTripsThatCorrespond() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        entityManager.persist(trip0);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        List<Trip> allTrips = tripRepository.findAvailableTrips("Chicago", "Miami", "2024-04-11");

        assertThat(allTrips).hasSize(2).extracting(Trip::getBusCompany).containsOnly(trip0.getBusCompany(), trip1.getBusCompany());
    }

    @Test
    void givenSetOfTrips_whenFindFromTo_thenReturnAllTripsThatCorrespond() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        entityManager.persist(trip0);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        List<Trip> allTrips = tripRepository.findAvailableTrips("Chicago", "Miami");

        assertThat(allTrips).hasSize(3).extracting(Trip::getBusCompany).containsOnly(trip0.getBusCompany(), trip1.getBusCompany(), trip2.getBusCompany());
    }

    @Test
    void givenSetOfTrips_whenFindDepartures_thenReturnDepartures() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        List<String> _departures = Arrays.asList("Chicago");

        entityManager.persist(trip0);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        List<String> departures = tripRepository.findDepartureCities();

        assertThat(departures).isEqualTo(_departures);
    }

    @Test
    void givenSetOfTrips_whenFindDestinations_thenReturnDepartures() {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "New York", "2024-04-12", "12:10", 200.0);

        List<String> _destinations = Arrays.asList("Miami", "New York");

        entityManager.persist(trip0);
        entityManager.persist(trip1);
        entityManager.persist(trip2);
        entityManager.flush();

        List<String> destinations = tripRepository.findDestinationCities();

        assertThat(destinations).isEqualTo(_destinations);
    }
}