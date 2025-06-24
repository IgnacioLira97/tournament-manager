import { Component } from '@angular/core';
import { MatchService } from '../../services/match.service';
import { Match } from '../../models/match.model';

@Component({
  selector: 'app-match',
  imports: [],
  templateUrl: './match.component.html',
  styleUrl: './match.component.scss'
})
export class MatchComponent  {
  matches: Match[] = [];

  constructor(private matchService: MatchService) {}

  ngOnInit(): void {
    this.fetchMatches();
  }

  fetchMatches(): void {
    this.matchService.getMatches().subscribe((data) => {
      this.matches = data;
    });
  }

  deleteMatch(id?: number): void {
    if (id != null) {
      this.matchService.deleteMatch(id).subscribe(() => {
        this.fetchMatches();
      });
    }
  }
}
