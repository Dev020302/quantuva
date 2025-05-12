package com.ticketing.repository;

import com.ticketing.model.Reply;
import com.ticketing.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByTicket(Ticket ticket);
}