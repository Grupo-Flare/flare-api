package br.com.flare.repositories;

import br.com.flare.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
   Optional<Feed> findByName(String feed);
}