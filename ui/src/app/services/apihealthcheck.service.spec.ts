import { TestBed } from '@angular/core/testing';

import { APIHealthcheckService } from './apihealthcheck.service';

describe('APIHealthcheckService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: APIHealthcheckService = TestBed.get(APIHealthcheckService);
    expect(service).toBeTruthy();
  });
});
