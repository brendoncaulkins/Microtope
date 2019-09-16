import { Component, OnInit } from '@angular/core';
import { TeamService } from 'src/app/services/team.service';
import { Team } from 'src/app/models/Team.model';
import { SelectedService } from 'src/app/services/selected.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-team-splitscreen-page',
  templateUrl: './team-splitscreen-page.component.html',
  styleUrls: ['./team-splitscreen-page.component.css'],
  providers: [SelectedService]
})
export class TeamSplitscreenPageComponent implements OnInit {

  constructor(private teamService:TeamService) { }

  teamsObs:Observable<Team[]>;

  ngOnInit() {
    this.teamsObs=this.teamService.getAll();
  }

}
