
package com.ticketing.dto;

import com.ticketing.model.Ticket;

public class TicketStatusRequest {
    private Ticket.Status status;

    public Ticket.Status getStatus() {
        return status;
    }

    public void setStatus(Ticket.Status status) {
        this.status = status;
    }
}