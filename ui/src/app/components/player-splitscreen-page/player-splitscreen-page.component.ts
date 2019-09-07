import { Component, OnInit } from '@angular/core';

import {SelectedService} from '../../services/selected.service';
import { PlayerService } from 'src/app/services/player.service';
import { Player } from 'src/app/models/Player.model';

@Component({
  selector: 'app-player-splitscreen-page',
  templateUrl: './player-splitscreen-page.component.html',
  styleUrls: ['./player-splitscreen-page.component.css'],
  providers: [SelectedService]
})
export class PlayerSplitscreenPageComponent implements OnInit {

  constructor(private playerService:PlayerService) { }

  players:Player[];

  ngOnInit() {
    this.playerService.getAll().subscribe(
      xs => this.players = xs
    )
  }

}
