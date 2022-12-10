package br.com.flare.controller.dto;

import br.com.flare.model.Feed;
import br.com.flare.model.Notification;
import br.com.flare.model.Urgency;

import java.io.Serializable;

/**
 * A DTO for the {@link br.com.flare.model.Notification} entity
 */
public class NotificationDto implements Serializable {
    private final String message;
    private final String feed;
    private final Urgency urgency;

    public NotificationDto(String message, String feed, Urgency urgency) {
        this.message = message;
        this.feed = feed;
        this.urgency = urgency;
    }

    public Notification toEntity(){
       return new Notification(this.message, new Feed(this.feed), this.urgency);
    }

    public String getMessage() {
        return message;
    }

    public String getFeed() {
        return feed;
    }

    public Urgency getUrgency() {
        return urgency;
    }

}