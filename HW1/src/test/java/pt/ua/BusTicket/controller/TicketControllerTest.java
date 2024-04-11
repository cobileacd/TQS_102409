package pt.ua.BusTicket.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Disabled;

import pt.ua.BusTicket.boundary.TicketController;
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

@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    // inject required beans as "mockeable" objects
    // note that @AutoWire would result in NoSuchBeanDefinitionException
    @MockBean
    private TicketService ticketService;

    @MockBean
    private TripService tripService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostTicket_thenCreateTicket( ) throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTripId(1L);
        ticket.setName("Foo");
        ticket.setAddress("Addr");
        ticket.setCity("City");
        ticket.setState("State");
        ticket.setZipCode("zip");
        ticket.setCardType("Visa");
        ticket.setCardNumber("123");
        ticket.setDateMonth("01");
        ticket.setDateYear("2025");
        ticket.setNameOnCard("Foo Bar");

        when( ticketService.save(Mockito.any()) ).thenReturn(ticket);

        Trip trip = new Trip("Some Bus Company", "Chicago", "Miami", "2024-04-11", "09:10", 200.0);
        when( tripService.getTripDetails(Mockito.any()) ).thenReturn(Optional.ofNullable(trip));

        mvc.perform(
                post("/api/reservations/purchase").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(ticket)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.name", is("Foo")));

        verify(ticketService, times(1)).save(Mockito.any());

    }

    @Test
    void whenGetTicketById_thenGetTicket( ) throws Exception {
        Ticket ticket = new Ticket();
        ticket.setTripId(1L);
        ticket.setName("Foo");
        ticket.setAddress("Addr");
        ticket.setCity("City");
        ticket.setState("State");
        ticket.setZipCode("zip");
        ticket.setCardType("Visa");
        ticket.setCardNumber("123");
        ticket.setDateMonth("01");
        ticket.setDateYear("2025");
        ticket.setNameOnCard("Foo Bar");

        when( ticketService.getTicketDetails(Mockito.any()) ).thenReturn(Optional.ofNullable(ticket));

        mvc.perform(
                get("/api/reservations/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.name", is("Foo")));
    }
}