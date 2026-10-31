package ru.rutmiit.basketball.dto;

import jakarta.validation.constraints.*;

public class AddTeamDto {

    @NotBlank(message = "Введите название команды")
    @Size(max = 100, message = "Не более 100 символов")
    private String name;

    @NotBlank(message = "Введите название города")
    @Size(max = 100, message = "Не более 100 символов")
    private String city;

    @NotBlank(message = "Введите описание команды")
    @Size(min = 10, max = 2000, message = "Описание должно содержать от 10 до 2000 символов")
    private String description;

    @NotNull(message = "Введите год основания команды")
    @Min(value = 1891, message = "Неверно указан год основания")
    @Max(value = 2026, message = "Неверно указан год основания")
    private Integer foundedYear;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public String getCity() {
        return city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
