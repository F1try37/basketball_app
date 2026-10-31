package ru.rutmiit.basketball.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rutmiit.basketball.dto.AddPlayerDto;
import ru.rutmiit.basketball.dto.PlayerSummary;
import ru.rutmiit.basketball.models.entities.Player;
import ru.rutmiit.basketball.models.entities.Team;
import ru.rutmiit.basketball.repositories.PlayerRepository;
import ru.rutmiit.basketball.repositories.TeamRepository;
import ru.rutmiit.basketball.services.exceptions.TeamNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PlayerServiceImpl implements PlayerService{
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public void addPlayer(AddPlayerDto form) {
        String teamName = form.getTeamName().trim();
        Team team = teamRepository.findByName(teamName).orElseThrow(() -> new TeamNotFoundException(teamName));
        Player player = new Player();
        player.setFirstName(form.getFirstName().trim());
        player.setLastName(form.getLastName().trim());
        player.setPosition(form.getPosition());
        player.setJerseyNumber(form.getJerseyNumber());
        player.setBirthDate(form.getBirthDate());
        player.setGamesPlayed(form.getGamesPlayed());
        player.setPointsPerGame(form.getPointsPerGame());
        player.setReboundsPerGame(form.getReboundsPerGame());
        player.setAssistsPerGame(form.getAssistsPerGame());
        player.setTeam(team);
        playerRepository.saveAndFlush(player);
    }

    @Override
    public List<PlayerSummary> allPlayers() {
        return playerRepository.findAllByOrderByLastNameAscFirstNameAsc().stream().map(this::toSummary).toList();
    }

    private PlayerSummary toSummary(Player player) {
        return new PlayerSummary(player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getPosition(),
                player.getJerseyNumber(),
                player.getBirthDate(),
                player.getGamesPlayed(),
                player.getPointsPerGame(),
                player.getReboundsPerGame(),
                player.getAssistsPerGame(),
                player.getTeam().getName());
    }
}
