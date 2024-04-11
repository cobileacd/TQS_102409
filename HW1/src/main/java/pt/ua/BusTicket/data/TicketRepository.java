package pt.ua.BusTicket.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pt.ua.BusTicket.data.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public Ticket findByTicketId(Long ticketId);
    public List<Ticket> findAll();

}