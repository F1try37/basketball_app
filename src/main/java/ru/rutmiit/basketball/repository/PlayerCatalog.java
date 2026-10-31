package ru.rutmiit.basketball.repository;

import ru.rutmiit.basketball.models.entities.Player;

import java.util.List;

public interface PlayerCatalog {
    List<Player> findAll();
}
