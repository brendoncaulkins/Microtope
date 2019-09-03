import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AppConfigService } from './services/app-config.service'

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

const appInitializerFn = (appConfig: AppConfigService) => {
  return () => {
      return appConfig.loadAppConfig();
  }
};

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
    AppConfigService,
        {
            provide: APP_INITIALIZER,
            useFactory: appInitializerFn,
            multi: true,
            deps: [AppConfigService]
        }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
