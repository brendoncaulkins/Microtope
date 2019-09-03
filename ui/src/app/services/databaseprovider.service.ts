import { Injectable,Inject } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DatabaseproviderService {

  constructor(
  ) { }

  public getUrl():string{return "lol"}
}
