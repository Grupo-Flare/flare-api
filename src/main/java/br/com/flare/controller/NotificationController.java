package br.com.flare.controller;

import br.com.flare.controller.dto.NotificationDto;
import br.com.flare.model.Feed;
import br.com.flare.model.Notification;
import br.com.flare.repositories.DeviceRepository;
import br.com.flare.repositories.FeedRepository;
import br.com.flare.repositories.NotificationRepository;
import br.com.flare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private DeviceRepository deviceRepository;
  @Autowired
  private NotificationRepository notificationRepository;
  @Autowired
  private FeedRepository feedRepository;

  @GetMapping("/list")
  public ResponseEntity<?> listAllNotifications() {

    List<Notification> notifications = notificationRepository.findAll();

    return ResponseEntity.ok(notifications);

  }

  @PostMapping("/send")
  public ResponseEntity<?> sendNotifications(@RequestBody NotificationDto notificationDto){

    Notification notification = notificationDto.toEntity();

    Optional<Feed> feed = feedRepository.findByName(notificationDto.getFeed().getName());
    if (feed.isEmpty()){
      return ResponseEntity.notFound().build();
    }

    notification.setFeed(feed.get());
    notification = notificationRepository.save(notification);

    return ResponseEntity.ok(notification);

  }

}
