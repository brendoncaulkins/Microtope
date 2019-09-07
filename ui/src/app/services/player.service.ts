import {Injectable} from '@angular/core';
import {map, tap, switchMap} from 'rxjs/operators';

import {Player} from '../models/Player.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private config:AppConfigService, private http: HttpClient) { }

  public getPlayers(): Observable<Player[]> {
    return this.config.loadAppConfig().pipe(
      tap(con => console.log("Got Config with base_url:" + con.api_url)),
      map(con => con.api_url+"/api/player_summary"),
      tap(url=> console.log("HTTPRequesting:" +url)),
      switchMap(url => this.http.get<Player[]>(url))
    );
  }

  public getPlayerByID(id: number): Observable<Player> {
    return this.getPlayers().pipe(
      map(obs => obs.filter(p=>p.id===id)),
      map(obs => obs[0])
    );
  }

  public getPlayerByName(name: string): Observable<Player> {
    return this.getPlayers().pipe(
      map(obs => obs.filter(p=>p.name===name)),
      map(obs => obs[0])
    );
  }

}
