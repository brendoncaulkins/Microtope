import { Component, OnInit } from '@angular/core';
import { SelectedPlayerService } from 'src/app/services/selected-player.service';

import {Player} from 'src/app/models/Player.model'

@Component({
  selector: 'app-player-splitscreen-page',
  templateUrl: './player-splitscreen-page.component.html',
  styleUrls: ['./player-splitscreen-page.component.css']
})
export class PlayerSplitscreenPageComponent implements OnInit {

  constructor(private selectedPlayerService:SelectedPlayerService) { }

  selectedPlayer:Player=null;

  ngOnInit() {
    this.selectedPlayerService.getPlayer().subscribe(x => this.selectedPlayer=x);
  }

}
