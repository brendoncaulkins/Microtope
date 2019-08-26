import { Injectable } from '@angular/core';

import {Player} from '../models/Player.model';

@Injectable({
  providedIn: 'root'
})
export class SelectedPlayerService {

  private selectedPlayer?:Player;

  constructor() { }

  public getPlayer():Player{
    return this.selectedPlayer;
  }

  public setPlayer(selected:Player):void{
    this.selectedPlayer=selected;
  }
}
