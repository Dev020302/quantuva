// ReplyController.java
package com.ticketing.controller;

import com.ticketing.dto.ReplyRequest;
import com.ticketing.dto.ReplyResponse;
import com.ticketing.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets/{ticketId}/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ResponseEntity<ReplyResponse> addReply(@PathVariable Long ticketId,
                                                  @RequestBody ReplyRequest replyRequest,
                                                  @RequestParam Long userId) {
        return ResponseEntity.ok(replyService.addReply(ticketId, replyRequest, userId));
    }

    @GetMapping
    public ResponseEntity<List<ReplyResponse>> getRepliesByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(replyService.getRepliesByTicketId(ticketId));
    }
}
