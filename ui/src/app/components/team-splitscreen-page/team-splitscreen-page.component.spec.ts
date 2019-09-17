import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamSplitscreenPageComponent } from './team-splitscreen-page.component';

describe('TeamSplitscreenPageComponent', () => {
  let component: TeamSplitscreenPageComponent;
  let fixture: ComponentFixture<TeamSplitscreenPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TeamSplitscreenPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamSplitscreenPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('has no items - should be created', () => {
    //component.teams = []
    
    expect(component).toBeTruthy();
  });
});
