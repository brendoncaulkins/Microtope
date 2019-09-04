import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';

import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {
  constructor(private http: HttpClient) {
    console.log('Initializising AppConfigService');
  }

  private getConfig(config: string): Observable<string> {
    return this.http.get<string>(config).pipe(
      tap(() => console.log('Looking for Config Files...'))
    );
  }

  public loadAppConfig(): Observable<string> {
    return this.getConfig(environment.configAddress).pipe(
      catchError(() => {
        console.log(`Couldn't find config, trying fallback config`);
        return this.getConfig(environment.configFallback);
      }),
      catchError(() => {
        console.log(`Couldn't find config, using default values`);
        return of('http://defaulturl.com');
      })
    );

  }
}
