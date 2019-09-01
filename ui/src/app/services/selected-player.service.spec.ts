import { TestBed } from '@angular/core/testing';

import { SelectedPlayerService } from './selected-player.service';

describe('SelectedPlayerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SelectedPlayerService = TestBed.get(SelectedPlayerService);
    expect(service).toBeTruthy();
  });
});
