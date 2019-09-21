
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
export class TeamDetailComponent {

  constructor(
    public selectedTeamService: SelectedService<Team>
    , private teamService: TeamService
  ) { }

  onClose() {
    this.selectedTeamService.deselect();
  }

  onSave(team: Team) {
    console.log('Saving current player...');
    this.teamService.updateTeam(team);
  }

}
