import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs';
import {AppConfigService} from './app-config.service';

import {PlayerService} from './player.service';

describe('PlayerService', () => {

  let httpSpy: jasmine.SpyObj<HttpClient>;
  let confSpy: jasmine.SpyObj<AppConfigService>;

  beforeEach(async () => {
    httpSpy = jasmine.createSpyObj<HttpClient>(['get', 'put']);
    confSpy = jasmine.createSpyObj<AppConfigService>(['loadAppConfig']);
  });

  it('should be created', doneFn => {
    // Arrange
    const service: PlayerService = new PlayerService(confSpy, httpSpy);
    confSpy.loadAppConfig.and.returnValue(of({api_url: 'fooUrl'} as any));
    httpSpy.get.and.returnValue(of([]));

    // Act
    service.getAll().subscribe(() => {

      // Assert
      expect(confSpy.loadAppConfig).toHaveBeenCalled();
      expect(httpSpy.get).toHaveBeenCalledWith('fooUrl/api/player_summary');
      doneFn();
    });
  });
});
