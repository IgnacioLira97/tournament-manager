import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Tournament } from '../models/tournament.model';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  private apiUrl = 'http://localhost:8080/tournaments'; // adjust to match your backend

  constructor(private http: HttpClient) {}

  getTournaments(): Observable<Tournament[]> {
    return this.http.get<Tournament[]>(this.apiUrl);
  }

  deleteTournament(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  createTournament(tournament: Partial<Tournament>): Observable<Tournament> {
    return this.http.post<Tournament>(this.apiUrl, tournament);
  }


  // You can add more methods like addTournament(), updateTournament(), etc. later
}
