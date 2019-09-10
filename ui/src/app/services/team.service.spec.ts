import { TestBed } from '@angular/core/testing';

import { TeamService } from './team.service';

import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

describe('TeamService', () => {

  beforeAll(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: HttpClient},
        {provide: AppConfigService}
      ]
    });
  });
  
  it('should be created', () => {
    const service: TeamService = TestBed.get(TeamService);
    expect(service).toBeTruthy();
  });
});
