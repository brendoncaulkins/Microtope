import {Component} from '@angular/core';
import {Player} from 'src/app/models/Player.model';
import {PlayerService} from 'src/app/services/player.service';
import {SelectedService} from 'src/app/services/selected.service';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent {

  constructor(
    public selectedPlayerService: SelectedService<Player>,
    private playerService: PlayerService
  ) { }

  onClose() {
    console.log('Closing current player without safe...');
    this.selectedPlayerService.deselect();
  }

  onSave(player: Player) {
    console.log('Saving current player...');
    console.log("currentPlayer is: " + JSON.stringify(player));
    this.playerService.updatePlayer(player);
  }

}
