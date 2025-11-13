package com.pkmtourney.tournament_manager.dto;

import com.pkmtourney.tournament_manager.enums.MatchOutcome;

import jakarta.validation.constraints.NotNull;

public record MatchRequest(
        @NotNull Long tournamentId,
        @NotNull Long playerOneId,
        @NotNull Long playerTwoId,
        @NotNull MatchOutcome outcome) {
}
