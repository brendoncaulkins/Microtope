import { TestBed } from '@angular/core/testing';

import { DatabaseproviderService } from './databaseprovider.service';

describe('DatabaseproviderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: DatabaseproviderService = TestBed.get(DatabaseproviderService);
    expect(service).toBeTruthy();
  });
});
