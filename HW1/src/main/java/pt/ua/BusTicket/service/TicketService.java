package pt.ua.BusTicket.service;

import org.springframework.stereotype.Service;

import pt.ua.BusTicket.data.TicketRepository;
import pt.ua.BusTicket.data.Ticket;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    final TicketRepository ticketRepo;

    public TicketService(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public Ticket save(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public List<Ticket> getAllTickets() {

        return ticketRepo.findAll();
    }

    public Optional<Ticket> getTicketDetails(Long ticketId) {
        return Optional.ofNullable(ticketRepo.findByTicketId(ticketId));
    }
}