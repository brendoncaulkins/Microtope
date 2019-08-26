import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerSplitscreenPageComponent } from './player-splitscreen-page.component';

describe('PlayerSplitscreenPageComponent', () => {
  let component: PlayerSplitscreenPageComponent;
  let fixture: ComponentFixture<PlayerSplitscreenPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayerSplitscreenPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerSplitscreenPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
