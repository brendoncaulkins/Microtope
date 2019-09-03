import { Injectable } from '@angular/core';

import {Player, IPlayer} from '../models/Player.model';
import {fakePlayers} from '../models/fakePlayers'; 
import { Observable,of } from 'rxjs';
import { DatabaseproviderService } from './databaseprovider.service';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  constructor(private databaseproviderservice:DatabaseproviderService) { }

  public getPlayers():Observable<Player[]>{
    console.log("URL Found: " + this.databaseproviderservice.getUrl())
    return of(fakePlayers);
  }

  public getPlayerByID(player_id:number):Observable<Player>{
    return of((fakePlayers.filter(x=>x.player_id===player_id))[0])
  }

  public getPlayerByName(player_name:string):Observable<Player>{
    return of((fakePlayers.filter(x=>x.player_name===player_name))[0])
  }
  
}
