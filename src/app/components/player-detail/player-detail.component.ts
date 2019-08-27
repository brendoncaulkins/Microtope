import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SelectedPlayerService } from 'src/app/services/selected-player.service';
import { Player } from 'src/app/models/Player.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit, OnDestroy {

  constructor( private selectedPlayerService:SelectedPlayerService) { }


  @Input() player:Player;

  selectedPlayerSubscription: Subscription;

  ngOnInit() {
    this.selectedPlayerSubscription= this.selectedPlayerService.player$.subscribe(
      newlySelectedPlayer => {this.player = newlySelectedPlayer}
    );
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }

  onClose(){
    this.selectedPlayerService.deselectPlayer();
  }

}
