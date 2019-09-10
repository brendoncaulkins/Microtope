import { TestBed } from '@angular/core/testing';

import { APIHealthcheckService } from './apihealthcheck.service';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';

describe('APIHealthcheckService', () => {
  beforeEach(() => {

    TestBed.configureTestingModule({
      providers: [APIHealthcheckService, HttpClient]
    });
  });
  it('should be created', () => {
    const service: APIHealthcheckService = TestBed.get(APIHealthcheckService);
    expect(service).toBeTruthy();
  });
});
