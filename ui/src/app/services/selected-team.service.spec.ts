import { TestBed } from '@angular/core/testing';

import { SelectedTeamService } from './selected-team.service';

describe('SelectedTeamService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SelectedTeamService = TestBed.get(SelectedTeamService);
    expect(service).toBeTruthy();
  });
});
