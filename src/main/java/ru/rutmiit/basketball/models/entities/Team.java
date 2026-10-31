package ru.rutmiit.basketball.models.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "description", length = 2000, nullable = false)
    private String description;

    @Column(name = "founded_year", nullable = false)
    private Integer foundedYear;

    @OneToMany(
            mappedBy = "team",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    private Set<Player> players = new HashSet<>();

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }
}
