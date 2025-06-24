import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- Import this
import { Tournament } from '../../models/tournament.model';
import { TournamentService } from '../../services/tournament.service';

@Component({
  selector: 'app-tournament',
  standalone: true,
  imports: [FormsModule], // <-- Add this
  templateUrl: './tournament.component.html',
  styleUrls: ['./tournament.component.scss'] // fix typo from "styleUrl"
})
export class TournamentComponent {
  tournaments: Tournament[] = [];

  // ✅ Define the newTournament object
  newTournament: Tournament = {
    name: '',
    date: '',     // use string because HTML date input returns a string
    status: 'PLANNED'
  };

  constructor(private tournamentService: TournamentService) {}

  ngOnInit(): void {
    this.fetchTournaments();
  }

  fetchTournaments(): void {
    this.tournamentService.getTournaments().subscribe((data) => {
      this.tournaments = data;
    });
  }

  deleteTournament(id?: number): void {
    if (id != null) {
      this.tournamentService.deleteTournament(id).subscribe(() => {
        this.fetchTournaments();
      });
    }
  }

  // ✅ No parameter — use this.newTournament directly
  createTournament(): void {
    this.tournamentService.createTournament(this.newTournament).subscribe(() => {
      this.fetchTournaments();

      // Optional: reset the form
      this.newTournament = {
        name: '',
        date: '',
        status: 'PLANNED'
      };
    });
  }
}

