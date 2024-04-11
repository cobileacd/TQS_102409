package pt.ua.BusTicket.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import pt.ua.BusTicket.boundary.TripController;
import pt.ua.BusTicket.service.TicketService;
import pt.ua.BusTicket.service.TripService;
import pt.ua.BusTicket.data.Ticket;
import pt.ua.BusTicket.data.Trip;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
class TripControllerTest {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    @MockBean
    private TripService tripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenGetTripById_thenGetTrip( ) throws Exception {
        Trip trip = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);

        when( tripService.getTripDetails(Mockito.any()) ).thenReturn(Optional.ofNullable(trip));

        mvc.perform(
                get("/api/trips/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.busCompany", is("Some Bus Company")));
    }

    @Test
    void whenGivenAListOfTripsFromTo_AndGetTripsFromTo_thenGetAllTripsThatCorrespond( ) throws Exception {
        Trip trip0 = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        Trip trip1 = new Trip("Another Bus Company", "Chicago", "Miami", "2024-04-11", "11:10", 200.0);
        Trip trip2 = new Trip("Other Bus Company", "Chicago", "Miami", "2024-04-12", "12:10", 200.0);

        List<Trip> allTrips = Arrays.asList(trip0, trip1, trip2);

        when( tripService.getAvailableTrips("Chicago", "Miami") ).thenReturn(allTrips);

        mvc.perform(
                get("/api/trips/Chicago/Miami").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].busCompany", is(trip0.getBusCompany())))
                .andExpect(jsonPath("$[1].busCompany", is(trip1.getBusCompany())))
                .andExpect(jsonPath("$[2].busCompany", is(trip2.getBusCompany())));

        verify(tripService, times(1)).getAvailableTrips("Chicago", "Miami");
    }
}