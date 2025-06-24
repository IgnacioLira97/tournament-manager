import { Player } from './player.model'; // or adjust path as needed

export interface Match {
  id?: number;
  player1: Player;
  player2: Player;
  winner?: Player | null; // optional, in case match isn't finished yet
  roundNumber: number;
}
