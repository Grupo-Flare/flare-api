package br.com.flare.sender;

import br.com.flare.model.Feed;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotificationSender {

    public void sendNotification(String msg, String feed, List<String> emails);

    public void sendNotification(String msg, Feed feed);

    public void sendNotification(String msg, String email);

}
