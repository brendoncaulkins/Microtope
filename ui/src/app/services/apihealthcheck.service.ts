import { Injectable } from '@angular/core';
import { AppConfigService } from './app-config.service';
import { HttpClient } from '@angular/common/http';
import {tap, switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class APIHealthcheckService {

  constructor(private config: AppConfigService, private http: HttpClient) { }

  public healthCheck(): void {
    let health = this.config.loadAppConfig().pipe(
      tap(x => console.log('Starting Healthcheck!')),
      switchMap( conf => this.http.get(conf.api_url + '/api/healthcheck')),
      tap(x => console.log('Found:' + JSON.stringify(x)))
    );
    }

}
