package br.com.flare.controller.dto;

import br.com.flare.model.Feed;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.flare.model.User} entity
 */
public class UserForm implements Serializable {
    private final String name;

    private final String email;

    private final List<String> feeds;

    public UserForm(String name, String email, List<String> feeds) {
        this.name = name;
        this.email = email;
        this.feeds = feeds;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getFeeds() {
        return feeds;
    }
}