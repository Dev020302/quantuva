
package com.ticketing.dto;

import com.ticketing.model.Reply;

public class ReplyResponse {
    private Long id;
    private String message;
    private UserDto user;

    public static ReplyResponse fromReply(Reply reply) {
        ReplyResponse response = new ReplyResponse();
        response.setId(reply.getId());
        response.setMessage(reply.getMessage());
        response.setUser(UserDto.fromUser(reply.getUser()));
        return response;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
