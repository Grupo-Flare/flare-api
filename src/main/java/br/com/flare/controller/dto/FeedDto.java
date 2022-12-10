package br.com.flare.controller.dto;

import br.com.flare.model.Feed;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.flare.model.Feed} entity
 */
public class FeedDto implements Serializable {
    private final String name;

    public FeedDto(String name) {
        this.name = name;
    }

    public Feed toEntity() {
        return new Feed(this.name);
    }

    public String getName() {
        return name;
    }

}