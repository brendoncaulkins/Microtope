import {Injectable} from '@angular/core';
import {map, tap} from 'rxjs/operators';

import {Player, IPlayer} from '../models/Player.model';
import {fakePlayers} from '../models/fakePlayers';
import {Observable, of} from 'rxjs';
import {DatabaseproviderService} from './databaseprovider.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private databaseproviderservice: DatabaseproviderService) { }

  public getPlayers(): Observable<Player[]> {
    return this.databaseproviderservice.getUrl().pipe(
      map(url => {
        return fakePlayers;
      })
    );
  }

  // Wenn deine Methode schon getPlayerById heißt, brauchst du deine parameter nicht player_id taufen...
  public getPlayerByID(id: number): Observable<Player> {
    return of((fakePlayers.filter(x => x.player_id === id))[0]);
  }

  public getPlayerByName(name: string): Observable<Player> {
    return of((fakePlayers.filter(x => x.player_name === name))[0]);
  }

}
