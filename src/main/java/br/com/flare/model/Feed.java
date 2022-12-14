package br.com.flare.model;

import br.com.flare.repositories.view.FeedView;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "feeds")
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany
    private List<User> users;

    public Feed() {
    }

    public Feed(String name) {
        this.name = name;
    }

    public Feed(FeedView feedView) {
       this.id = feedView.getId();
       this.name = feedView.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
