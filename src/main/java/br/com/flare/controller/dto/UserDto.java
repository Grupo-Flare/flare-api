package br.com.flare.controller.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.flare.model.User} entity
 */
public class UserDto implements Serializable {
    private final String name;
    private final String email;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}