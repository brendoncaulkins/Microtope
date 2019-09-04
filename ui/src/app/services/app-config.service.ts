import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import { Observable,of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

    private appConfig:any;

    constructor(private http:HttpClient) {
      console.log("Initializising AppConfigService");
      this.loadAppConfig();
      console.log("Made AppConfigService");
    }

    private loadAppConfig():void {
        console.log("Looking for Config Files...");
        this.http.get(environment.configAddress)
        .toPromise()
        .then(data => {
            console.info("Found Primary Config File");
            this.appConfig = data;
        })
        .catch(error => {
          this.http.get(environment.configFallback)
          .toPromise()
          .then(fallbackdata => {
            console.info("Found Fallback Config File");
            this.appConfig = fallbackdata;
          }).catch(error => {
            console.warn("Error loading app-config.json, using default object instead");
            this.appConfig = {
              apiurl:"http://defaulturl.com"
            };
            })
        })
        
    }

    public getURL():string{
      return this.appConfig.apiurl;
    }
}
