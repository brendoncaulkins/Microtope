import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

import {environment} from '../../environments/environment';

import {IConfig} from '../models/IConfig';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  
  constructor(private http: HttpClient) {}

  private getConfig(configLocationURL: string): Observable<IConfig> {
    return this.http.get<IConfig>(configLocationURL).pipe(
      tap(() => console.log('Looking for Config Files...'))
    );
  }

  public loadAppConfig(): Observable<IConfig> {
    return this.getConfig(environment.configAddress).pipe(
      catchError(() => {
        console.log(`Couldn't find config, trying fallback config`);
        return this.getConfig(environment.configFallback);
      }),
      catchError(() => {
        console.log(`Couldn't find config, using default values`);
        return this.defaultConfig();
      })
    );

  }

  private defaultConfig(): Observable<IConfig>{
    const defaultConfig ={
      api_url:"http://defaulturl.com",
      api_user: "defaulter",
      api_pwd:"unusable"
    }
    return of( defaultConfig as IConfig);
  }
}
