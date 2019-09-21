import { TestBed } from '@angular/core/testing';

import { APIHealthcheckService } from './apihealthcheck.service';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('APIHealthcheckService', () => {


  let masterService: APIHealthcheckService;
  let valueServiceSpy: jasmine.SpyObj<AppConfigService>;

  beforeEach(() => {
    const confSpy = jasmine.createSpyObj('AppConfigService', ['loadAppConfig']);

    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      // Provide both the service-to-test and its (spy) dependency
      providers: [
        APIHealthcheckService,
        { provide: AppConfigService, useValue: confSpy }
      ]
    });
    // Inject both the service-to-test and its (spy) dependency
    masterService = TestBed.get(APIHealthcheckService);
    valueServiceSpy = TestBed.get(AppConfigService);
  });

  it('should be created', () => {
    const service: APIHealthcheckService = TestBed.get(APIHealthcheckService);
    expect(service).toBeTruthy();
  });
});
