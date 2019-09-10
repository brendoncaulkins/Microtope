
import { AppConfigService } from './app-config.service';
import { HttpClientModule } from '@angular/common/http';

import { TestBed } from '@angular/core/testing';

describe('AppConfigService', () => {
  beforeEach(() => {
      TestBed.configureTestingModule({
          imports: [HttpClientModule],
          providers: [AppConfigService]
      });
  });
  
  it('should be created', () => {
    const service: AppConfigService = TestBed.get(AppConfigService);
    expect(service).toBeTruthy();
  });

});

