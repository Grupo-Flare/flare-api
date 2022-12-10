package br.com.flare.controller.dto;

import br.com.flare.model.Feed;
import br.com.flare.model.Notification;
import br.com.flare.model.Urgency;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.flare.model.Notification} entity
 */
public class NotificationDto implements Serializable {
    private final String message;
    private final FeedDto feed;
    private final Urgency urgency;

    public NotificationDto(String message, FeedDto feed, Urgency urgency) {
        this.message = message;
        this.feed = feed;
        this.urgency = urgency;
    }

    public Notification toEntity(){
       return new Notification(this.message, this.feed.toEntity(), this.urgency);
    }

    public String getMessage() {
        return message;
    }

    public FeedDto getFeed() {
        return feed;
    }

    public Urgency getUrgency() {
        return urgency;
    }

}