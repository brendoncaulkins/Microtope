
import { AppConfigService } from './app-config.service';
import { HttpClientModule } from '@angular/common/http';

import { TestBed } from '@angular/core/testing';
import { HttpClient } from 'selenium-webdriver/http';

describe('AppConfigService', () => {
  let service:AppConfigService;
  beforeEach(async () => {
      TestBed.configureTestingModule({
          imports: [HttpClientModule],
          providers: [HttpClient]
      });
      
  });


  it('should be created', () => {
    const service: AppConfigService = new AppConfigService(TestBed.get(HttpClient));
    expect(service).toBeTruthy();
  });

});

