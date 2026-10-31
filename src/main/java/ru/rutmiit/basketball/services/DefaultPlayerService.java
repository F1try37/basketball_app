package ru.rutmiit.basketball.services;

import org.springframework.stereotype.Service;
import ru.rutmiit.basketball.repository.PlayerCatalog;
import ru.rutmiit.basketball.models.entities.Player;

import java.util.List;

@Service
public class DefaultPlayerService implements PlayerService{

    private final PlayerCatalog playerCatalog;

    public DefaultPlayerService(PlayerCatalog playerCatalog) {
        this.playerCatalog = playerCatalog;
    }

    @Override
    public List<Player> allPlayers() {
        return playerCatalog.findAll();
    }
}
