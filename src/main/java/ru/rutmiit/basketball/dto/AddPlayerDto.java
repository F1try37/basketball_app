package ru.rutmiit.basketball.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.rutmiit.basketball.models.enums.Position;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddPlayerDto {

    private String firstName;

    private String lastName;

    private Position position;

    private Integer jerseyNumber;

    private LocalDate birthDate;

    private Integer gamesPlayed;

    private BigDecimal pointsPerGame;

    private BigDecimal reboundsPerGame;

    private BigDecimal assistsPerGame;

    private String teamName;

    @NotBlank(message = "Введите имя")
    @Size(max = 100, message = "Не более 100 символов")
    public String getFirstName() {
        return firstName;
    }

    @NotBlank(message = "Введите фамилию")
    @Size(max = 100, message = "Не более 100 символов")
    public String getLastName() {
        return lastName;
    }

    @NotNull(message = "Выберите позицию")
    public Position getPosition() {
        return position;
    }

    @NotNull(message = "Введите игровой номер")
    @Min(value = 0, message = "Номер должен быть от 0 до 99")
    @Max(value = 99, message = "Номер должен быть от 0 до 99")
    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    @NotNull(message = "Укажите дату рождения")
    @Past(message = "Дата рождения должна быть в прошлом")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @NotNull(message = "Введите число матчей")
    @Min(value = 0, message = "Не может быть отрицательным")
    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    @NotNull(message = "Введите очки за игру")
    @DecimalMin(value = "0.0", message = "Не может быть отрицательным")
    @Digits(integer = 2, fraction = 1, message = "Не более 2 цифр до точки и 1 после")
    public BigDecimal getPointsPerGame() {
        return pointsPerGame;
    }

    @NotNull(message = "Введите подборы за игру")
    @DecimalMin(value = "0.0", message = "Не может быть отрицательным")
    @Digits(integer = 2, fraction = 1, message = "Не более 2 цифр до точки и 1 после")
    public BigDecimal getReboundsPerGame() {
        return reboundsPerGame;
    }

    @NotNull(message = "Введите передачи за игру")
    @DecimalMin(value = "0.0", message = "Не может быть отрицательным")
    @Digits(integer = 2, fraction = 1, message = "Не более 2 цифр до точки и 1 после")
    public BigDecimal getAssistsPerGame() {
        return assistsPerGame;
    }

    @NotBlank(message = "Выберите команду")
    @Size(max = 100, message = "Не более 100 символов")
    public String getTeamName() {
        return teamName;
    }

    public void setFirstName(String firstName) {
        this.firstName = normalize(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = normalize(lastName);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setPointsPerGame(BigDecimal pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public void setReboundsPerGame(BigDecimal reboundsPerGame) {
        this.reboundsPerGame = reboundsPerGame;
    }

    public void setAssistsPerGame(BigDecimal assistsPerGame) {
        this.assistsPerGame = assistsPerGame;
    }

    public void setTeamName(String teamName) {
        this.teamName = normalize(teamName);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }
}
