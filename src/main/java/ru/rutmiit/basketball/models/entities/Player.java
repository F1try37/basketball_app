package ru.rutmiit.basketball.models.entities;

import jakarta.persistence.*;
import ru.rutmiit.basketball.models.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "position", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "jersey_number", nullable = false)
    private Integer jerseyNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "games_played", nullable = false)
    private Integer gamesPlayed;

    @Column(name = "points_per_game",precision = 3, scale = 1, nullable = false)
    private BigDecimal pointsPerGame;

    @Column(name = "rebounds_per_game", precision = 3, scale = 1, nullable = false)
    private BigDecimal reboundsPerGame;

    @Column(name = "assists_per_game", precision = 3, scale = 1, nullable = false)
    private BigDecimal assistsPerGame;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "team_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_player_team")
    )
    private Team team;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
        return position;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public BigDecimal getPointsPerGame() {
        return pointsPerGame;
    }

    public BigDecimal getReboundsPerGame() {
        return reboundsPerGame;
    }

    public BigDecimal getAssistsPerGame() {
        return assistsPerGame;
    }

    public Team getTeam() {
        return team;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setAssistsPerGame(BigDecimal assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public void setReboundsPerGame(BigDecimal reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public void setPointsPerGame(BigDecimal pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
