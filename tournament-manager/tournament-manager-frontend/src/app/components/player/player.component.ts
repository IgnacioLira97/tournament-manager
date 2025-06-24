import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../../services/player.service';
import { Player } from '../../models/player.model';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.scss'] // <-- note the plural: styleUrls
})
export class PlayerComponent implements OnInit {
  players: Player[] = [];

  constructor(private playerService: PlayerService) {}

  ngOnInit(): void {
    this.fetchPlayers();
  }

  fetchPlayers(): void {
    this.playerService.getPlayers().subscribe((data) => {
      this.players = data;
    });
  }

  deletePlayer(id?: number): void {
    if (id != null) {
      this.playerService.deletePlayer(id).subscribe(() => {
        this.fetchPlayers(); // refresh list after deletion
      });
    }
  }
}
