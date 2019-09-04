import { Injectable,Inject } from '@angular/core';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class DatabaseproviderService {

  constructor(private appConfig:AppConfigService
  ) { 
    console.log("Initializising DatabaseproviderService");}

  public getUrl():string{
    return this.appConfig.getURL();
  }
}
