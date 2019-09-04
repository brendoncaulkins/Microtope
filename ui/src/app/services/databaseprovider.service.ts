import {Injectable, Inject} from '@angular/core';
import {Observable} from 'rxjs';
import {AppConfigService} from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class DatabaseproviderService {

  constructor(private appConfig: AppConfigService) {}

  public getUrl(): Observable<string> {
    return this.appConfig.loadAppConfig();
  }
}
