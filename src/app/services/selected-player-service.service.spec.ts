import { TestBed } from '@angular/core/testing';

import { SelectedPlayerServiceService } from './selected-player-service.service';

describe('SelectedPlayerServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SelectedPlayerServiceService = TestBed.get(SelectedPlayerServiceService);
    expect(service).toBeTruthy();
  });
});
