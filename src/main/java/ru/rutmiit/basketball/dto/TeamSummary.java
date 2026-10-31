package ru.rutmiit.basketball.dto;

public record TeamSummary(
   String name,
   String city,
   String description,
   Integer foundedYear
) {
}
