import { TestBed } from '@angular/core/testing';

import { PlayerService } from './player.service';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

describe('PlayerService', () => {
  beforeAll(() => {

    TestBed.configureTestingModule({
      providers: [
        {provide: HttpClient},
        {provide: AppConfigService}
      ]
    });
  });

  it('should be created', () => {
    const service: PlayerService = TestBed.get(PlayerService);
    expect(service).toBeTruthy();
  });

});

