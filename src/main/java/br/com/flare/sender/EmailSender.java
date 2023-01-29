package br.com.flare.sender;

import br.com.flare.model.Feed;
import br.com.flare.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;

@Component
public class EmailSender implements NotificationSender {

    @Autowired
    private FeedRepository feedRepository;

    public JavaMailSenderImpl getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("192.168.100.77");
        mailSender.setPort(25);

        mailSender.setUsername("flare@127.0.0.1");
        mailSender.setPassword("flare");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Override
    public void sendNotification(String msg, String feed, List<String> emails) {
        JavaMailSenderImpl emailSender = getJavaMailSender();

        for (int i = 0; i < emails.size(); i++) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("flare@127.0.0.1");
            message.setTo(emails.get(i));
            message.setSubject("Notificação de " + feed);
            message.setText(msg);
            emailSender.send(message);
        }

    }

    @Override
    public void sendNotification(String msg, Feed feed) {

        List<String> emails = feedRepository.findUsersByFeedName(feed.getName());

        sendNotification(msg, feed.getName(), emails);
    }

    @Override
    public void sendNotification(String msg, String email) {

        JavaMailSenderImpl emailSender = getJavaMailSender();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("flare@127.0.0.1");
        message.setTo(email);
        message.setSubject("Notificação");
        message.setText(msg);
        emailSender.send(message);

    }
}
