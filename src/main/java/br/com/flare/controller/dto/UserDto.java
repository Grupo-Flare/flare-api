package br.com.flare.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.flare.model.User} entity
 */
public class UserDto implements Serializable {
    @NotEmpty(message = "Deve conter o nome do usuario")
    private final String name;

    @NotEmpty(message = "Deve conter o email em um formato valido")
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