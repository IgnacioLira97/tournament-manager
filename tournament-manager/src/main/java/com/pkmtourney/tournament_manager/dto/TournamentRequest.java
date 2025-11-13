package com.pkmtourney.tournament_manager.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record TournamentRequest(@NotBlank String name, LocalDate startDate) {
}
