import {Injectable, Inject} from '@angular/core';
import {Observable} from 'rxjs';
import {AppConfigService} from './app-config.service';
import { IConfig } from '../models/IConfig';

@Injectable({
  providedIn: 'root'
})
export class DatabaseproviderService {

  constructor(private appConfig: AppConfigService) {}

  public getUrl(): Observable<IConfig> {
    return this.appConfig.loadAppConfig();
  }
}
