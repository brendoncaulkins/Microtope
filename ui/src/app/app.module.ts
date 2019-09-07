import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AppConfigService } from './services/app-config.service'

import {environment} from '../environments/environment.prod'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SplitscreenComponent } from './components/splitscreen/splitscreen.component';
import { TeamSplitscreenPageComponent } from './components/team-splitscreen-page/team-splitscreen-page.component';
import { PlayerSplitscreenPageComponent } from './components/player-splitscreen-page/player-splitscreen-page.component';
import { PlayerDetailComponent } from './components/player-detail/player-detail.component';
import { TeamDetailComponent } from './components/team-detail/team-detail.component';

import {HttpClientModule} from "@angular/common/http";
import { IPreviewableListComponent } from './components/ipreviewable-list/ipreviewable-list.component';
import { TopRankableComponent } from './components/top-rankable/top-rankable.component';


@NgModule({
  declarations: [
    AppComponent,
    SplitscreenComponent,
    TeamSplitscreenPageComponent,
    PlayerSplitscreenPageComponent,
    PlayerDetailComponent,
    TeamDetailComponent,
    IPreviewableListComponent,
    TopRankableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
