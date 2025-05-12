
package com.ticketing.dto;

import com.ticketing.model.User;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private User.UserRole role;

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User.UserRole getRole() {
        return role;
    }

    public void setRole(User.UserRole role) {
        this.role = role;
    }
}
