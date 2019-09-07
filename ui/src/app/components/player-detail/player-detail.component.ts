import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';
import { Player } from 'src/app/models/Player.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit, OnDestroy {

  constructor( private selectedPlayerService:SelectedService<Player>) { }


  @Input() player:Player;

  selectedPlayerSubscription: Subscription;

  ngOnInit() {
    this.selectedPlayerSubscription= this.selectedPlayerService.subject$.subscribe(
      newlySelectedPlayer => {this.player = newlySelectedPlayer}
    );
  }

  ngOnDestroy(){
    this.selectedPlayerSubscription && this.selectedPlayerSubscription.unsubscribe();
  }

  onClose(){
    this.selectedPlayerService.deselect();
  }

}
