package ru.rutmiit.basketball.services;

import ru.rutmiit.basketball.dto.AddPlayerDto;
import ru.rutmiit.basketball.dto.PlayerSummary;

import java.util.List;

public interface PlayerService {

    List<PlayerSummary> allPlayers();

    void addPlayer(AddPlayerDto form);
}
