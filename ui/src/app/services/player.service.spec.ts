import { TestBed } from '@angular/core/testing';

import { PlayerService } from './player.service';
import { HttpClient } from '@angular/common/http';
import { AppConfigService } from './app-config.service';

describe('PlayerService', () => {

  let playerService: PlayerService;
  let valueServiceSpy: jasmine.SpyObj<AppConfigService>;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeAll(async () => {
    const confSpy = jasmine.createSpyObj('AppConfigService', ['getValue']);
    const httpSpy = jasmine.createSpyObj('HttpClient', ['get','put','push']);
  
    TestBed.configureTestingModule({
      providers: [
        PlayerService,
        { provide: AppConfigService, useValue: confSpy },
        { provide: HttpClient, useValue: httpSpy}
      ]
    });

    // Inject both the service-to-test and its (spy) dependency
      valueServiceSpy = TestBed.get(AppConfigService);
      httpClientSpy = TestBed.get(HttpClient);
      playerService = TestBed.get(PlayerService);
  });

  it('should be created', () => {
    const service: PlayerService = TestBed.get(PlayerService);
    expect(service).toBeTruthy();
  });
});