import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SplitscreenComponent } from './splitscreen/splitscreen.component';
import { PlayerListComponent } from './player-list/player-list.component';
import { TeamListComponent } from './team-list/team-list.component';
import { TopTeamsComponent } from './top-teams/top-teams.component';
import { TopPlayersComponent } from './top-players/top-players.component';
import { TeamSplitscreenPageComponent } from './team-splitscreen-page/team-splitscreen-page.component';
import { PlayerSplitscreenPageComponent } from './player-splitscreen-page/player-splitscreen-page.component';
import { PlayerDetailComponent } from './player-detail/player-detail.component';
import { TeamDetailComponent } from './team-detail/team-detail.component';

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
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
