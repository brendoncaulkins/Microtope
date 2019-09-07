import { Component, OnInit, OnDestroy } from '@angular/core';
import { PlayerService } from 'src/app/services/player.service';
import { SelectedService } from 'src/app/services/selected.service';

import {Player} from 'src/app/models/Player.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent implements OnInit, OnDestroy {

  constructor(private playerService:PlayerService, private selectedPlayerService:SelectedService<Player>) { }

  players:Player[];
  selectedPlayer:Player;


  selectedPlayerSubscription: Subscription;
  

  ngOnInit() {
    this.getPlayers();
    this.selectedPlayerSubscription= this.selectedPlayerService.subject$.subscribe(
      newlySelectedPlayer => {this.selectedPlayer = newlySelectedPlayer});
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }

  getPlayers(): void {
    this.playerService.getAll()
        .subscribe(players => this.players = players);
  }

  onSelect(player:Player): void {
    this.selectedPlayerService.set(player);
  }

}
