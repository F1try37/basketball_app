package ru.rutmiit.basketball.dto;

import jakarta.validation.constraints.*;

public class AddTeamDto {

    private String name;

    private String city;

    private String description;

    private Integer foundedYear;

    @NotBlank(message = "Введите название команды")
    @Size(max = 100, message = "Не более 100 символов")
    public String getName() {
        return name;
    }

    @NotBlank(message = "Введите описание команды")
    @Size(min = 10, max = 2000, message = "Описание должно содержать от 10 до 2000 символов")
    public String getDescription() {
        return description;
    }

    @NotNull(message = "Введите год основания команды")
    @Min(value = 1891, message = "Неверно указан год основания")
    @Max(value = 2026, message = "Неверно указан год основания")
    public Integer getFoundedYear() {
        return foundedYear;
    }

    @NotBlank(message = "Введите название города")
    @Size(max = 100, message = "Не более 100 символов")
    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = normalize(name);
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public void setDescription(String description) {
        this.description = normalize(description);
    }

    public void setCity(String city) {
        this.city = normalize(city);
    }

    private String normalize(String value) {
        return value == null ? null : value.trim();
    }
}
