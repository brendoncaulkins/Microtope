import { TestBed } from '@angular/core/testing';

import { SelectedTeamServiceService } from './selected-team-service.service';

describe('SelectedTeamServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SelectedTeamServiceService = TestBed.get(SelectedTeamServiceService);
    expect(service).toBeTruthy();
  });
});
