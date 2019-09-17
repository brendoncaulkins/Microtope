import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, tap, switchMap} from 'rxjs/operators';
import {Observable} from 'rxjs';

import { AppConfigService } from './app-config.service';

import {Team} from "../models/Team.model";
import { IPreviewableService } from './IPreviewable.service';


@Injectable({
  providedIn: 'root'
})
export class TeamService extends IPreviewableService<Team> {

  private TEAM_API:string = "/api/team";
  private TEAM_SUMMARY_API:string = "/api/team_summary"

  constructor(private config:AppConfigService, private http: HttpClient) {super();}

  public getAll(): Observable<Team[]> {
    return this.config.loadAppConfig().pipe(
      tap(con => console.log("Got Config with base_url:" + con.api_url)),
      map(con => con.api_url+this.TEAM_SUMMARY_API),
      tap(url=> console.log("HTTPRequesting:" +url)),
      switchMap(url => this.http.get<Team[]>(url))
    );
  }

  public updateTeam(team:Team):void{
    this.config.loadAppConfig().pipe(
      tap(con => console.log("Got Config with base_url:" + con.api_url)),
      map(con => con.api_url+this.TEAM_API),
      tap(url=> console.log("HTTPRequesting:" +url)),
      switchMap(url => 
        this.http.put(url+"/"+team.id, {id:team.id, name:team.name} )
      )
    );
  }

}
