
package com.ticketing.dto;

import com.ticketing.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private Ticket.Priority priority;
    private Ticket.Status status;
    private UserDto customer;
    private UserDto agent;
    private List<ReplyResponse> replies;

    public static TicketResponse fromTicket(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.setId(ticket.getId());
        response.setTitle(ticket.getTitle());
        response.setDescription(ticket.getDescription());
        response.setPriority(ticket.getPriority());
        response.setStatus(ticket.getStatus());

        if (ticket.getCustomer() != null) {
            response.setCustomer(UserDto.fromUser(ticket.getCustomer()));
        }

        if (ticket.getAgent() != null) {
            response.setAgent(UserDto.fromUser(ticket.getAgent()));
        }

        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket.Priority getPriority() {
        return priority;
    }

    public void setPriority(Ticket.Priority priority) {
        this.priority = priority;
    }

    public Ticket.Status getStatus() {
        return status;
    }

    public void setStatus(Ticket.Status status) {
        this.status = status;
    }

    public UserDto getCustomer() {
        return customer;
    }

    public void setCustomer(UserDto customer) {
        this.customer = customer;
    }

    public UserDto getAgent() {
        return agent;
    }

    public void setAgent(UserDto agent) {
        this.agent = agent;
    }

    public List<ReplyResponse> getReplies() {
        return replies;
    }

    public void setReplies(List<ReplyResponse> replies) {
        this.replies = replies;
    }
}
