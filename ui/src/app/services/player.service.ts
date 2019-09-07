import {Injectable} from '@angular/core';
import {map, tap, switchMap} from 'rxjs/operators';


import {Player} from '../models/Player.model';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { AppConfigService } from './app-config.service';
import { IPreviewableService } from './IPreviewable.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService extends IPreviewableService<Player> {

  constructor(private config:AppConfigService, private http: HttpClient) {super();}

  public getAll(): Observable<Player[]> {
    return this.config.loadAppConfig().pipe(
      tap(con => console.log("Got Config with base_url:" + con.api_url)),
      map(con => con.api_url+"/api/player_summary"),
      tap(url=> console.log("HTTPRequesting:" +url)),
      switchMap(url => this.http.get<Player[]>(url))
    );
  }

}
