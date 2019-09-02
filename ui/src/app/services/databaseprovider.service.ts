import { Injectable,Inject } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DatabaseproviderService {

  constructor(
    @Inject('BACKEND_API_URL') private apiUrl: string,
    @Inject('BACKEND_API_USER') private apiUser: string,
    @Inject('BACKEND_API_PWD') private apiPwd: string
  ) { }

  public getUrl():string{ return this.apiUrl;}
}
