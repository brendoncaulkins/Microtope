import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';
import { Player } from 'src/app/models/Player.model';
import { Subscription } from 'rxjs';
import { PlayerService } from 'src/app/services/player.service';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit, OnDestroy {

  constructor( private selectedPlayerService:SelectedService<Player>, private playerService:PlayerService) { }


  @Input() player:Player;

  selectedPlayerSubscription: Subscription;

  ngOnInit() {
    this.selectedPlayerSubscription= this.selectedPlayerService.selected$.subscribe(
      newlySelectedPlayer => {this.player = newlySelectedPlayer}
    );
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }

  onClose(){
    console.log("Closing current player without safe...")
    this.selectedPlayerService.deselect();
  }

  onSave(){
    console.log("Saving current player...")
    this.playerService.updatePlayer(this.player);
  }
  

}
