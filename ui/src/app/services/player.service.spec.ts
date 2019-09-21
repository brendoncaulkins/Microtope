import { TestBed } from '@angular/core/testing';

import { PlayerService } from './player.service';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('PlayerService', () => {

  let playerService: PlayerService;
  let valueServiceSpy: jasmine.SpyObj<AppConfigService>;

  beforeAll(async () => {
    const confSpy = jasmine.createSpyObj('AppConfigService', ['getValue']);

    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [
        PlayerService,
        { provide: AppConfigService, useValue: confSpy }
      ]
    });

    // Inject both the service-to-test and its (spy) dependency
    valueServiceSpy = TestBed.get(AppConfigService);
    playerService = TestBed.get(PlayerService);
  });

  it('should be created', () => {
    const service: PlayerService = TestBed.get(PlayerService);
    expect(service).toBeTruthy();
  });
});
