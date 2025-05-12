
package com.ticketing.repository;

import com.ticketing.model.Ticket;
import com.ticketing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCustomer(User customer);
    List<Ticket> findByAgent(User agent);
    List<Ticket> findByAgentIsNull();
    List<Ticket> findByStatus(Ticket.Status status);
}