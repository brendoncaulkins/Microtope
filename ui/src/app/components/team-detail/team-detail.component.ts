
import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SelectedService } from 'src/app/services/selected.service';
import { Team } from '../../models/Team.model';
import { Subscription } from 'rxjs';
import { TeamService } from 'src/app/services/team.service';
@Component({
  selector: 'app-team-detail',
  templateUrl: './team-detail.component.html',
  styleUrls: ['./team-detail.component.css']
})
export class TeamDetailComponent implements OnInit, OnDestroy {

  selectedTeamSub: Subscription;
  constructor( private selectionService:SelectedService<Team>, private teamService:TeamService) { }

  @Input() team:Team;

  ngOnInit() {
    this.selectedTeamSub= this.selectionService.selected$.subscribe(
      x => {this.team = x}
    );
  }

  ngOnDestroy(){
    this.selectedTeamSub && this.selectedTeamSub.unsubscribe();
  }

  onClose(){
    this.selectionService.deselect();
  }

  onSave(){
    console.log("Saving current player...")
    this.teamService.updateTeam(this.team);
  }
  
}
