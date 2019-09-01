import { Injectable } from '@angular/core';

import {Player} from '../models/Player.model';
import { Observable,of,BehaviorSubject } from 'rxjs';

import {  } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SelectedPlayerService {
  private playerValue:Player = null;
  private player: BehaviorSubject<Player> = new BehaviorSubject<Player>(null);

  player$: Observable<Player> = this.player.asObservable();

  constructor() { }

  public getPlayer():Observable<Player>{
    return this.player$;
  }

  public setPlayer(selected:Player):void{
    this.playerValue=selected;
    this.player.next(this.playerValue);
    console.log("PServ Selected Player " + selected.player_id);
  }

  public deselectPlayer():void{
    this.playerValue=null;
    this.player.next(this.playerValue);
    console.log("PServ deselected Player");
  }

}
/*
Subscriptionbehavior taken from
https://stackoverflow.com/questions/53377973/angular-6-update-a-value-in-my-component-when-she-change-in-my-service
*/
