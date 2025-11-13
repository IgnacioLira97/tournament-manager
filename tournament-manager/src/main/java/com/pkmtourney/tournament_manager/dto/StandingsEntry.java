package com.pkmtourney.tournament_manager.dto;

public record StandingsEntry(Long playerId, String playerName, int wins, int losses, int ties, int points) {
}
