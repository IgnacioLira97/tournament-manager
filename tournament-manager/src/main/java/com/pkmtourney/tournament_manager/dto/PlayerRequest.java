package com.pkmtourney.tournament_manager.dto;

import jakarta.validation.constraints.NotBlank;

public record PlayerRequest(@NotBlank String name) {
}
