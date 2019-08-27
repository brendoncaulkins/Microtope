import { Injectable } from '@angular/core';

import {Player} from '../models/Player.model';
import { Observable,of } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SelectedPlayerService {

  private selectedPlayer?:Player = null;

  constructor() { }

  public getPlayer():Observable<Player>{
    return of(this.selectedPlayer);
  }

  public setPlayer(selected:Player):void{
    console.log("PServ Selected Player " + selected.player_id);
    this.selectedPlayer=selected;
  }

  public deselectPlayer():void{
    this.selectedPlayer=null;
  }

}
