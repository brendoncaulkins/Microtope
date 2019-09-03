import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

    private appConfig:any;

    constructor(private injector: Injector) {}

    loadAppConfig() {
        let http = this.injector.get(HttpClient);
        return http.get('/assets/app-config.json')
        .toPromise()
        .then(data => {
            this.appConfig = data;
        })
        .catch(error => {
          console.warn("Error loading app-config.json, using default object instead");
          this.appConfig = {
            apiurl:"http://defaulturl.com"
          };
      })
    }

    public getURL(){
      return this.appConfig.apiurl;
    }
}
