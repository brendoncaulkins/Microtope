import { Component, OnInit } from '@angular/core';

import {SelectedService} from '../../services/selected.service';

@Component({
  selector: 'app-player-splitscreen-page',
  templateUrl: './player-splitscreen-page.component.html',
  styleUrls: ['./player-splitscreen-page.component.css'],
  providers: [SelectedService]
})
export class PlayerSplitscreenPageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
