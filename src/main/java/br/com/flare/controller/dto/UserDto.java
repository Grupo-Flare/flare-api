package br.com.flare.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.flare.model.User} entity
 */
public class UserDto implements Serializable {
    @NotNull
    private final String name;

    @NotNull
    @Email
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