import { Injectable } from '@angular/core';

import {Team} from '../models/Team.model';

@Injectable({
  providedIn: 'root'
})
export class SelectedTeamService {

  private selectedTeam?:Team;

  constructor() { }


  public getTeam():Team{
    return this.selectedTeam;
  }

  public setTeam(selected:Team):void{
    this.selectedTeam=selected;
  }

  public deselectTeam():void{
    this.selectedTeam=null;
  }
}
