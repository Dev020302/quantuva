
package com.ticketing.service;

import com.ticketing.dto.ReplyRequest;
import com.ticketing.dto.ReplyResponse;
import com.ticketing.model.Reply;
import com.ticketing.model.Ticket;
import com.ticketing.model.User;
import com.ticketing.repository.ReplyRepository;
import com.ticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    public ReplyResponse addReply(Long ticketId, ReplyRequest replyRequest, Long userId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        User user = userService.getUserById(userId);

        // Validate if user can reply to this ticket
        if (user.getRole() == User.UserRole.CUSTOMER && !ticket.getCustomer().getId().equals(user.getId())) {
            throw new RuntimeException("You can only reply to your own tickets");
        } else if (user.getRole() == User.UserRole.AGENT &&
                (ticket.getAgent() == null || !ticket.getAgent().getId().equals(user.getId()))) {
            throw new RuntimeException("You can only reply to tickets assigned to you");
        }

        Reply reply = new Reply();
        reply.setMessage(replyRequest.getMessage());
        reply.setTicket(ticket);
        reply.setUser(user);

        Reply savedReply = replyRepository.save(reply);

        return ReplyResponse.fromReply(savedReply);
    }

    public List<ReplyResponse> getRepliesByTicketId(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        List<Reply> replies = replyRepository.findByTicket(ticket);

        return replies.stream()
                .map(ReplyResponse::fromReply)
                .collect(Collectors.toList());
    }
}
