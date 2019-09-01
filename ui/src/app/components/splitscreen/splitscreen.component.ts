import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-splitscreen',
  template: `
    <app-player-splitscreen-page></app-player-splitscreen-page>
    <app-team-splitscreen-page></app-team-splitscreen-page>
  `,
  styles: [`
    :host{
      display:flex;
      justify-content: space-evenly;
    }
  `]
})
export class SplitscreenComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
