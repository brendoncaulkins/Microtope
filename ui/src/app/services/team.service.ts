import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, tap, switchMap} from 'rxjs/operators';
import {Observable} from 'rxjs';

import { AppConfigService } from './app-config.service';

import {Team} from "../models/Team.model";

import {filterByName,filterByID} from "../shared/IPreviewableUtis";


@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private config:AppConfigService, private http: HttpClient) { }

  public getTeams():Observable<Team[]>{
    return this.config.loadAppConfig().pipe(
      tap(con => console.log("Got Config with base_url:" + con.api_url)),
      map(con => con.api_url+"/api/player_summary"),
      tap(url=> console.log("HTTPRequesting:" +url)),
      switchMap(url => this.http.get<Team[]>(url))
    );
  }

  public getTeamByID(id:number):Observable<Team>{
    return filterByID(this.getTeams(),id);
  }

  public getTeamByName(name:string):Observable<Team>{
    return filterByName(this.getTeams(),name);
  }
  
}