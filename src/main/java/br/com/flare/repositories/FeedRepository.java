package br.com.flare.repositories;

import br.com.flare.model.Feed;
import br.com.flare.repositories.view.FeedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
   @Query(value = "SELECT f.id, f.\"name\" FROM feeds f WHERE \"name\" = :feed", nativeQuery = true)
   Optional<FeedView> findByName(String feed);
}