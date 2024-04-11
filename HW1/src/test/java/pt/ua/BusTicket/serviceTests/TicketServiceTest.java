package pt.ua.BusTicket.serviceTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import pt.ua.BusTicket.service.TicketService;
import pt.ua.BusTicket.data.Ticket;
import pt.ua.BusTicket.data.TicketRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest{

    // prevent strict stubbing
    @Mock( lenient = true )
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        Ticket ticket0 = new Ticket(1L, "Foo", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Foo Bar");
        Ticket ticket1 = new Ticket(1L, "Bar", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Bar Foo");
        Ticket ticket2 = new Ticket(1L, "Baz", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Baz Bar");

        Mockito.when(ticketRepository.findByTicketId(1L)).thenReturn(ticket0);
        Mockito.when(ticketRepository.findByTicketId(2L)).thenReturn(ticket1);
        Mockito.when(ticketRepository.findByTicketId(3L)).thenReturn(ticket2);
        Mockito.when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket0, ticket1, ticket2));
    }

    @Test
    void whenSearchForValidId_thenTicketShouldBeFound() {
        Ticket found = ticketService.getTicketDetails(1L).get();
        assertThat(found.getName()).isEqualTo("Foo");
    }

    @Test
    void whenSearchInvalidId_thenTicketShouldNotBeFound() {
        assertThat(ticketService.getTicketDetails(5L).isPresent()).isFalse();

        Mockito.verify(ticketRepository, Mockito.times(1)).findByTicketId(Mockito.anyLong());
    }

    @Test
    void whenGetTickets_thenReturnAll3Tickets() {
        Ticket ticket0 = new Ticket(1L, "Foo", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Foo Bar");
        Ticket ticket1 = new Ticket(1L, "Bar", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Bar Foo");
        Ticket ticket2 = new Ticket(1L, "Baz", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Baz Bar");

        List<Ticket> allTickets = ticketService.getAllTickets();
        assertThat(allTickets).hasSize(3).extracting(Ticket::getName).contains(ticket0.getName(), ticket1.getName(), ticket2.getName());

        Mockito.verify(ticketRepository, Mockito.times(1)).findAll();
    }

}