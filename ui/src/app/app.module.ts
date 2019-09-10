import { BrowserModule } from '@angular/platform-browser';
import { NgModule,CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
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
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }
