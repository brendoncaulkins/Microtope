import { Injectable, Injector } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

    private appConfig:any;

    constructor(private injector: Injector) {}

    loadAppConfig() {
        console.log("Loading App Config");
        
        let http = this.injector.get(HttpClient);
        
        console.log("Got HTTP Client");
        
        return http.get('/assets/app-config.json')
        .toPromise()
        .then(data => {
            console.log("found:" + data)  
            this.appConfig = data;
        })
        .catch(error => {
          console.warn("Error loading app-config.json, using envrionment file instead");
          this.appConfig = {
            apiurl:"http://defaulturl.com"
          };
      })
    }

    public getURL(){
      console.log(this.appConfig.apiurl)
      return this.appConfig.apiurl;
    }
}
