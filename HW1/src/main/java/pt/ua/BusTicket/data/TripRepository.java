package pt.ua.BusTicket.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.ua.BusTicket.data.Trip;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    public Trip findByTripId(Long tripId);
    public List<Trip> findAll();

    @Query("SELECT DISTINCT t.dep FROM Trip t")
    public List<String> findDepartureCities();

    @Query("SELECT DISTINCT t.dest FROM Trip t")
    public List<String> findDestinationCities();

    @Query("SELECT t FROM Trip t WHERE t.dep = :fromPort AND t.dest = :toPort AND t.date = :tripDate")
    public List<Trip> findAvailableTrips(String fromPort, String toPort, String tripDate);

    @Query("SELECT t FROM Trip t WHERE t.dep = :fromPort AND t.dest = :toPort")
    public List<Trip> findAvailableTrips(String fromPort, String toPort);
}