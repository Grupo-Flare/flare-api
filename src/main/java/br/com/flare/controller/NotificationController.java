package br.com.flare.controller;

import br.com.flare.controller.dto.NotificationDto;
import br.com.flare.model.Feed;
import br.com.flare.model.Notification;
import br.com.flare.repositories.FeedRepository;
import br.com.flare.repositories.NotificationRepository;
import br.com.flare.repositories.UserRepository;
import br.com.flare.repositories.view.FeedView;
import br.com.flare.sender.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private NotificationRepository notificationRepository;
  @Autowired
  private FeedRepository feedRepository;

  @Autowired
  private NotificationSender notificationSender;

  @GetMapping("/list")
  public ResponseEntity<?> listAllNotifications() {

    List<Notification> notifications = notificationRepository.findAll();

    List<NotificationDto> notificationDtos = new ArrayList<>();
    notifications.forEach(notification -> notificationDtos.add(notification.toDTO()));

    return ResponseEntity.ok(notificationDtos);

  }

  @GetMapping(value = "/list/{feed}")
  public ResponseEntity<?> listByFeed(@PathVariable String feed){

    Optional<FeedView> byFeed = feedRepository.findByName(feed);
    if (byFeed.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feed não encontrado");
    }

    List<Notification> notifications = notificationRepository.findByFeed(new Feed(byFeed.get()));
    if (notifications.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há nenhuma notificação neste feed");
    }

    List<NotificationDto> notificationDtos = new ArrayList<>();
    notifications.forEach(notification -> notificationDtos.add(notification.toDTO()));

    return ResponseEntity.ok(notificationDtos);

  }

  @PostMapping("/send")
  public ResponseEntity<?> sendNotifications(@RequestBody NotificationDto notificationDto){

    Notification notification = notificationDto.toEntity();

    Optional<FeedView> feed = feedRepository.findByName(notificationDto.getFeed());
    if (feed.isEmpty()){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Feed não encontrado");
    }

    notificationSender.sendNotification(notification.getMessage(), notification.getFeed());

    notification.setFeed(new Feed(feed.get()));
    notification = notificationRepository.save(notification);

    return ResponseEntity.ok(notification.toDTO());

  }

}
