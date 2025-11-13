import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Player {
  id: number;
  name: string;
}

interface Tournament {
  id: number;
  name: string;
  startDate?: string;
}

interface MatchPlayer {
  id: number;
  name: string;
}

interface Match {
  id: number;
  playerOne: MatchPlayer;
  playerTwo: MatchPlayer;
  outcome: string;
  playedAt: string;
}

interface StandingsEntry {
  playerId: number;
  playerName: string;
  wins: number;
  losses: number;
  ties: number;
  points: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  players: Player[] = [];
  tournaments: Tournament[] = [];
  matches: Match[] = [];
  standings: StandingsEntry[] = [];

  newPlayerName = '';
  newTournamentName = '';
  newTournamentDate = '';
  selectedTournamentId: number | null = null;

  matchForm = {
    tournamentId: '',
    playerOneId: '',
    playerTwoId: '',
    outcome: 'PLAYER_ONE_WIN'
  };

  matchOutcomes = [
    { value: 'PLAYER_ONE_WIN', label: 'Player 1 Win' },
    { value: 'PLAYER_TWO_WIN', label: 'Player 2 Win' },
    { value: 'TIE', label: 'Tie' }
  ];

  bannerMessage = '';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.refreshPlayers();
    this.refreshTournaments();
  }

  refreshPlayers(): void {
    this.http.get<Player[]>('/api/players').subscribe((data) => (this.players = data));
  }

  refreshTournaments(): void {
    this.http.get<Tournament[]>('/api/tournaments').subscribe((data) => {
      this.tournaments = data;
      if (this.selectedTournamentId) {
        const exists = data.some((t) => t.id === this.selectedTournamentId);
        if (!exists) {
          this.selectedTournamentId = null;
          this.matches = [];
          this.standings = [];
        } else {
          this.loadTournamentDetails(this.selectedTournamentId);
        }
      }
    });
  }

  createPlayer(): void {
    if (!this.newPlayerName.trim()) {
      return;
    }
    this.http
      .post<Player>('/api/players', { name: this.newPlayerName.trim() })
      .subscribe({
        next: (player) => {
          this.players = [...this.players, player].sort((a, b) => a.name.localeCompare(b.name));
          this.newPlayerName = '';
          this.setBanner(`${player.name} joined the roster.`);
        },
        error: () => this.setBanner('Failed to create player.')
      });
  }

  createTournament(): void {
    if (!this.newTournamentName.trim()) {
      return;
    }
    const payload: any = { name: this.newTournamentName.trim() };
    if (this.newTournamentDate) {
      payload.startDate = this.newTournamentDate;
    }
    this.http.post<Tournament>('/api/tournaments', payload).subscribe({
      next: (tournament) => {
        this.tournaments = [tournament, ...this.tournaments];
        this.newTournamentName = '';
        this.newTournamentDate = '';
        this.setBanner(`Tournament "${tournament.name}" created.`);
      },
      error: () => this.setBanner('Failed to create tournament.')
    });
  }

  recordMatch(): void {
    if (!this.matchForm.tournamentId || !this.matchForm.playerOneId || !this.matchForm.playerTwoId) {
      return;
    }
    const payload = {
      tournamentId: Number(this.matchForm.tournamentId),
      playerOneId: Number(this.matchForm.playerOneId),
      playerTwoId: Number(this.matchForm.playerTwoId),
      outcome: this.matchForm.outcome
    };
    this.http.post<Match>('/api/matches', payload).subscribe({
      next: () => {
        this.setBanner('Match recorded.');
        this.matchForm.playerOneId = '';
        this.matchForm.playerTwoId = '';
        if (this.selectedTournamentId === payload.tournamentId) {
          this.loadTournamentDetails(payload.tournamentId);
        }
      },
      error: () => this.setBanner('Failed to record match.')
    });
  }

  selectTournament(id: string): void {
    const numericId = Number(id);
    this.selectedTournamentId = numericId;
    this.loadTournamentDetails(numericId);
  }

  private loadTournamentDetails(tournamentId: number): void {
    if (!tournamentId) {
      return;
    }
    this.http.get<Match[]>(`/api/tournaments/${tournamentId}/matches`).subscribe((matches) => {
      this.matches = matches;
    });
    this.http.get<StandingsEntry[]>(`/api/tournaments/${tournamentId}/standings`).subscribe((rows) => {
      this.standings = rows;
    });
  }

  private setBanner(message: string): void {
    this.bannerMessage = message;
    setTimeout(() => (this.bannerMessage = ''), 4000);
  }
}
