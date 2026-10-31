package ru.rutmiit.basketball.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.rutmiit.basketball.models.entities.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    @EntityGraph(attributePaths = "team")
    List<Player> findAllByOrderByLastNameAscFirstNameAsc();
}
