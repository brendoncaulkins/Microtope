import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {environment} from '../environments/environment.prod'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SplitscreenComponent } from './components/splitscreen/splitscreen.component';
import { PlayerListComponent } from './components/player-list/player-list.component';
import { TeamListComponent } from './components/team-list/team-list.component';
import { TopTeamsComponent } from './components/top-teams/top-teams.component';
import { TopPlayersComponent } from './components/top-players/top-players.component';
import { TeamSplitscreenPageComponent } from './components/team-splitscreen-page/team-splitscreen-page.component';
import { PlayerSplitscreenPageComponent } from './components/player-splitscreen-page/player-splitscreen-page.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    SplitscreenComponent,
    PlayerListComponent,
    TeamListComponent,
    TopTeamsComponent,
    TopPlayersComponent,
    TeamSplitscreenPageComponent,
    PlayerSplitscreenPageComponent,
    PlayerDetailComponent,
    TeamDetailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    {provide: 'BACKEND_API_URL', useValue: environment.backendApiUrl},
    {provide: 'BACKEND_API_USER', useValue: environment.backendUserName},
    {provide: 'BACKEND_API_PWD', useValue: environment.backendPassword},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
