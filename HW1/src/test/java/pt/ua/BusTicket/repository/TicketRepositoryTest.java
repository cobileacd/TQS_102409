package pt.ua.BusTicket.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import pt.ua.BusTicket.data.TicketRepository;
import pt.ua.BusTicket.data.Ticket;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DataJpaTest limits the test scope to the data access context (no web environment loaded, for example)
 * tries to autoconfigure the database, if possible (e.g.: in memory db)
 */
@DataJpaTest
public class TicketRepositoryTest {

    // get a test-friendly Entity Manager
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void whenFindTicketByExistingId_thenReturnEmployee() {
        Ticket ticket = new Ticket(1L, "Foo", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Foo Bar");
        entityManager.persistAndFlush(ticket);

        Ticket fromDb = ticketRepository.findByTicketId(ticket.getTicketId());
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getName()).isEqualTo(ticket.getName());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Ticket fromDb = ticketRepository.findByTicketId(-111L);
        assertThat(fromDb).isNull();
    }

    @Test
    void givenSetOfTickets_whenFindAll_thenReturnAllTickets() {
        Ticket ticket0 = new Ticket(1L, "Foo", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Foo Bar");
        Ticket ticket1 = new Ticket(1L, "Bar", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Bar Foo");
        Ticket ticket2 = new Ticket(1L, "Baz", "Addr", "City", "State", "zip", "Visa", "123", "01", "2025", "Baz Bar");

        entityManager.persist(ticket0);
        entityManager.persist(ticket1);
        entityManager.persist(ticket2);
        entityManager.flush();

        List<Ticket> allTickets = ticketRepository.findAll();

        assertThat(allTickets).hasSize(3).extracting(Ticket::getName).containsOnly(ticket0.getName(), ticket1.getName(), ticket2.getName());
    }
}