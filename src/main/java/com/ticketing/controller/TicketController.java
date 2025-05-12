
package com.ticketing.controller;

import com.ticketing.dto.TicketAssignRequest;
import com.ticketing.dto.TicketRequest;
import com.ticketing.dto.TicketResponse;
import com.ticketing.dto.TicketStatusRequest;
import com.ticketing.model.Ticket;
import com.ticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest ticketRequest,
                                                       @RequestParam Long userId) {
        return ResponseEntity.ok(ticketService.createTicket(ticketRequest, userId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TicketResponse>> getTicketsForCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(ticketService.getTicketsForCustomer(customerId));
    }

    @GetMapping("/agent/{agentId}")
    public ResponseEntity<List<TicketResponse>> getTicketsForAgent(@PathVariable Long agentId) {
        return ResponseEntity.ok(ticketService.getTicketsForAgent(agentId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<TicketResponse>> getUnassignedTickets() {
        return ResponseEntity.ok(ticketService.getUnassignedTickets());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TicketResponse>> getTicketsByStatus(@PathVariable Ticket.Status status) {
        return ResponseEntity.ok(ticketService.getTicketsByStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PutMapping("/{id}/assign")
    public ResponseEntity<TicketResponse> assignTicket(@PathVariable Long id, @RequestBody TicketAssignRequest request) {
        return ResponseEntity.ok(ticketService.assignTicket(id, request.getAgentId()));
    }

    @PutMapping("/{id}/unassign")
    public ResponseEntity<TicketResponse> unassignTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.unassignTicket(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TicketResponse> updateTicketStatus(@PathVariable Long id, @RequestBody TicketStatusRequest request) {
        return ResponseEntity.ok(ticketService.updateTicketStatus(id, request.getStatus()));
    }
}