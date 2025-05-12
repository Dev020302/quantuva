
package com.ticketing.service;

import com.ticketing.dto.TicketRequest;
import com.ticketing.dto.TicketResponse;
import com.ticketing.model.Ticket;
import com.ticketing.model.User;
import com.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReplyService replyService;

    public TicketResponse createTicket(TicketRequest ticketRequest, Long userId) {
        User customer = userService.getUserById(userId);

        if (customer.getRole() != User.UserRole.CUSTOMER) {
            throw new RuntimeException("Only customers can create tickets");
        }

        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequest.getTitle());
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setPriority(ticketRequest.getPriority());
        ticket.setStatus(Ticket.Status.OPEN);
        ticket.setCustomer(customer);

        Ticket savedTicket = ticketRepository.save(ticket);

        TicketResponse response = TicketResponse.fromTicket(savedTicket);
        response.setReplies(replyService.getRepliesByTicketId(savedTicket.getId()));

        return response;
    }

    public List<TicketResponse> getTicketsForCustomer(Long customerId) {
        User customer = userService.getUserById(customerId);
        List<Ticket> tickets = ticketRepository.findByCustomer(customer);

        return tickets.stream()
                .map(ticket -> {
                    TicketResponse response = TicketResponse.fromTicket(ticket);
                    response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getTicketsForAgent(Long agentId) {
        User agent = userService.getUserById(agentId);
        List<Ticket> tickets = ticketRepository.findByAgent(agent);

        return tickets.stream()
                .map(ticket -> {
                    TicketResponse response = TicketResponse.fromTicket(ticket);
                    response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(ticket -> {
                    TicketResponse response = TicketResponse.fromTicket(ticket);
                    response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getUnassignedTickets() {
        List<Ticket> tickets = ticketRepository.findByAgentIsNull();

        return tickets.stream()
                .map(ticket -> {
                    TicketResponse response = TicketResponse.fromTicket(ticket);
                    response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getTicketsByStatus(Ticket.Status status) {
        List<Ticket> tickets = ticketRepository.findByStatus(status);

        return tickets.stream()
                .map(ticket -> {
                    TicketResponse response = TicketResponse.fromTicket(ticket);
                    response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));
                    return response;
                })
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        TicketResponse response = TicketResponse.fromTicket(ticket);
        response.setReplies(replyService.getRepliesByTicketId(ticket.getId()));

        return response;
    }

    public TicketResponse assignTicket(Long ticketId, Long agentId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User agent = userService.getUserById(agentId);

        if (agent.getRole() != User.UserRole.AGENT) {
            throw new RuntimeException("Can only assign tickets to agents");
        }

        ticket.setAgent(agent);
        ticket.setStatus(Ticket.Status.IN_PROGRESS);

        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketResponse response = TicketResponse.fromTicket(updatedTicket);
        response.setReplies(replyService.getRepliesByTicketId(updatedTicket.getId()));

        return response;
    }

    public TicketResponse unassignTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setAgent(null);
        if (ticket.getStatus() == Ticket.Status.RESOLVED) {
            ticket.setStatus(Ticket.Status.OPEN);
        }

        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketResponse response = TicketResponse.fromTicket(updatedTicket);
        response.setReplies(replyService.getRepliesByTicketId(updatedTicket.getId()));

        return response;
    }

    public TicketResponse updateTicketStatus(Long ticketId, Ticket.Status status) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);

        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketResponse response = TicketResponse.fromTicket(updatedTicket);
        response.setReplies(replyService.getRepliesByTicketId(updatedTicket.getId()));

        return response;
    }
}