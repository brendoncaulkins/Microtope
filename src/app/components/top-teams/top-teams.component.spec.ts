import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopTeamsComponent } from './top-teams.component';

describe('TopTeamsComponent', () => {
  let component: TopTeamsComponent;
  let fixture: ComponentFixture<TopTeamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopTeamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
