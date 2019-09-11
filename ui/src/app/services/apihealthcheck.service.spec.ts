import { TestBed } from '@angular/core/testing';

import { APIHealthcheckService } from './apihealthcheck.service';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

describe('APIHealthcheckService', () => {


  let masterService: APIHealthcheckService;
  let valueServiceSpy: jasmine.SpyObj<AppConfigService>;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    const confSpy = jasmine.createSpyObj('AppConfigService', ['loadAppConfig']);
    const httpSpy = jasmine.createSpyObj('HttpClient', ['get','put','push']);

    TestBed.configureTestingModule({
      // Provide both the service-to-test and its (spy) dependency
      providers: [
        APIHealthcheckService,
        { provide: AppConfigService, useValue: confSpy },
        { provide: HttpClient, useValue: httpSpy}
      ]
    });
    // Inject both the service-to-test and its (spy) dependency
    masterService = TestBed.get(APIHealthcheckService);
    valueServiceSpy = TestBed.get(AppConfigService);
    httpClientSpy = TestBed.get(HttpClient);
  });

  it('should be created', () => {
    const service: APIHealthcheckService = TestBed.get(APIHealthcheckService);
    expect(service).toBeTruthy();
  });
});
