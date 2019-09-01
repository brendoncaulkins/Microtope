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

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
