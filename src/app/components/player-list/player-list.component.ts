import { Component, OnInit } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { SelectedPlayerService } from 'src/app/services/selected-player.service';

import {Player} from 'src/app/models/Player.model';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit {

  constructor(private playerService:PlayerService, private selectedPlayerService:SelectedPlayerService) { }

  players:Player[];
  selectedPlayer:Player;

  ngOnInit() {
    this.getPlayers();
    this.selectedPlayerService.getPlayer().subscribe(x=>this.selectedPlayer=x);
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
