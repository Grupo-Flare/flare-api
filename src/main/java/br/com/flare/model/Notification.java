package br.com.flare.model;

import br.com.flare.controller.dto.FeedDto;
import br.com.flare.controller.dto.NotificationDto;
import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String message;
    @ManyToOne
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private Urgency urgency;

    public Notification() {
    }

    public Notification(String message, Feed feed, Urgency urgency) {
        this.message = message;
        this.feed = feed;
        this.urgency = urgency;
    }

    public NotificationDto toDTO(){
        return new NotificationDto(this.message, this.feed.toDTO(), this.urgency);
    }

    public Long getId() {
        return id;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
