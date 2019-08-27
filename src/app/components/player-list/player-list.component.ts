import { Component, OnInit, OnDestroy } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { SelectedPlayerService } from 'src/app/services/selected-player.service';

import {Player} from 'src/app/models/Player.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit, OnDestroy {

  constructor(private playerService:PlayerService, private selectedPlayerService:SelectedPlayerService) { }

  players:Player[];
  selectedPlayer:Player;


  selectedPlayerSubscription: Subscription;

  ngOnInit() {
    this.getPlayers();
    this.selectedPlayerSubscription= this.selectedPlayerService.player$.subscribe(
      newlySelectedPlayer => {this.selectedPlayer = newlySelectedPlayer});
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }

  getPlayers(): void {
    this.playerService.getPlayers()
        .subscribe(players => this.players = players);
  }

  onSelect(player:Player): void {
    console.log("Selected Player " + player.player_id);
    this.selectedPlayerService.setPlayer(player);
  }

}
