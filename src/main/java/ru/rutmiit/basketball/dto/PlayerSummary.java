package ru.rutmiit.basketball.dto;

import ru.rutmiit.basketball.models.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PlayerSummary(
        UUID id,
        String firstName,
        String lastName,
        Position position,
        Integer jerseyNumber,
        LocalDate birthDate,
        Integer gamesPlayed,
        BigDecimal pointsPerGame,
        BigDecimal reboundsPerGame,
        BigDecimal assistsPerGame,
        String teamName
) {
}
