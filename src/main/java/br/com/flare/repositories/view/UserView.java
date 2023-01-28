package br.com.flare.repositories.view;

import br.com.flare.model.User;

import java.util.List;

/**
 * A Projection for the {@link User} entity
 */
public interface UserView {
    String getName();

    String getEmail();

    List<String> getFeeds();
}