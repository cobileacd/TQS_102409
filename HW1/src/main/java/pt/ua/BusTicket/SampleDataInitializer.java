package pt.ua.BusTicket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pt.ua.BusTicket.data.TripRepository;
import pt.ua.BusTicket.data.Trip;

import java.util.List;
import java.util.Arrays;

@Generated
@Component
public class SampleDataInitializer implements CommandLineRunner {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Trip> sampleTrips = Arrays.asList(
            new Trip("Express Travel", "Chicago", "Miami", "2024-04-15", "09:30", 200),
            new Trip("Express Travel", "Chicago", "Miami", "2024-04-15", "10:30", 200),
            new Trip("Express Travel", "Chicago", "Miami", "2024-04-15", "11:30", 200),
            new Trip("Express Travel", "Chicago", "Miami", "2024-04-15", "12:30", 200),
            new Trip("Express Travel", "Chicago", "Miami", "2024-04-11", "13:30", 200),
            new Trip("Transit Solutions", "Boston", "San Francisco", "2024-04-20", "08:45", 180),
            new Trip("Metro Transport", "Seattle", "Denver", "2024-04-18", "10:15", 160),
            new Trip("City Shuttle", "Atlanta", "Las Vegas", "2024-04-22", "12:00", 220),
            new Trip("Rapid Lines", "Philadelphia", "Dallas", "2024-04-25", "11:30", 190),
            new Trip("Coastal Breeze", "Portland", "Houston", "2024-04-17", "09:00", 210),
            new Trip("Urban Express", "Orlando", "Phoenix", "2024-04-19", "08:00", 230),
            new Trip("Sunrise Transit", "Miami", "Seattle", "2024-04-14", "07:45", 250),
            new Trip("City Connect", "San Diego", "New Orleans", "2024-04-21", "10:30", 180),
            new Trip("Transcontinental Travel", "Houston", "Boston", "2024-04-16", "11:15", 200),
            new Trip("Skyway Shuttle", "Las Vegas", "Chicago", "2024-04-23", "09:45", 220),
            new Trip("Metro Movers", "San Francisco", "Philadelphia", "2024-04-24", "08:30", 190),
            new Trip("Central Connections", "Dallas", "Atlanta", "2024-04-13", "12:30", 210),
            new Trip("Transit Tracks", "Denver", "Portland", "2024-04-26", "10:00", 170),
            new Trip("Express Lines", "New Orleans", "San Diego", "2024-04-27", "11:45", 200),
            new Trip("City Sprints", "Phoenix", "Seattle", "2024-04-28", "09:15", 230),
            new Trip("Sunset Shuttles", "Boston", "Miami", "2024-04-29", "08:45", 240),
            new Trip("Transit Titans", "Chicago", "Dallas", "2024-04-30", "10:30", 190),
            new Trip("Metropolis Movers", "Los Angeles", "Las Vegas", "2024-04-10", "12:15", 170),
            new Trip("Cityscape Connect", "Seattle", "New York", "2024-04-11", "11:00", 250)
        );

        tripRepository.saveAll(sampleTrips);
    }
}