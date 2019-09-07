import { Component, OnInit, OnDestroy } from '@angular/core';
import { Player } from 'src/app/models/Player.model';

import { PlayerService } from 'src/app/services/player.service';
import { SelectedService } from 'src/app/services/selected.service';

import { Subscription } from 'rxjs';


@Component({
  selector: 'app-top-players',
  templateUrl: './top-players.component.html',
  styleUrls: ['./top-players.component.css']
})
export class TopPlayersComponent implements OnInit, OnDestroy {

  constructor(private playerService:PlayerService, private selectedPlayerService:SelectedService<Player>) { }

  selectedPlayer:Player;

  selectedPlayerSubscription: Subscription;

  ngOnInit() {

    this.selectedPlayerSubscription= this.selectedPlayerService.subject$.subscribe(
      newlySelectedPlayer => {this.selectedPlayer = newlySelectedPlayer});
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }
}
