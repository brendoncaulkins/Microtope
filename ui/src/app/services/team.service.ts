import { Injectable } from '@angular/core';

import {Team, ITeam} from '../models/Team.model';
import {fakeTeams} from '../models/faketeams'; 
import { Observable,of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor() { }

  public getTeams():Observable<Team[]>{
    return of(fakeTeams);
  }

  public getTeamByID(team_id:number):Observable<Team>{
    return of((fakeTeams.filter(x=>x.team_id===team_id))[0])
  }

  public getTeamByName(team_name:string):Observable<Team>{
    return of((fakeTeams.filter(x=>x.team_name===team_name))[0])
  }
  
}
