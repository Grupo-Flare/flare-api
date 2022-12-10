package br.com.flare.repositories;

import br.com.flare.model.Feed;
import br.com.flare.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByFeed(Feed feed);
}
