import { Component, OnInit, Input, ChangeDetectionStrategy } from '@angular/core';
import { SelectedPlayerService } from 'src/app/services/selected-player.service';
import { Player } from 'src/app/models/Player.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class PlayerDetailComponent implements OnInit {

  constructor( private selectedPlayerService:SelectedPlayerService) { }

  //@Input() player:Player;

  @Input() player:Player;

  ngOnInit() {
    this.selectedPlayerService.getPlayer().subscribe(x=>this.player=x);
  }


  onClose(){
    this.selectedPlayerService.deselectPlayer();
  }

}
