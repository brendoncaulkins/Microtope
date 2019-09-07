import { Injectable } from '@angular/core';

import { Observable,BehaviorSubject } from 'rxjs';

/*
Subscriptionbehavior taken from
https://stackoverflow.com/questions/53377973/angular-6-update-a-value-in-my-component-when-she-change-in-my-service
*/


@Injectable({
  providedIn: 'root'
})
export class SelectedService<T> {

  constructor() { }

  private value:T = null;
  private subject: BehaviorSubject<T> = new BehaviorSubject<T>(null);

  subject$: Observable<T> = this.subject.asObservable();

  public get():Observable<T>{
    return this.subject$;
  }

  public set(selected:T):void{
    this.value=selected;
    this.subject.next(this.value);
  }

  public deselect():void{
    this.value=null;
    this.subject.next(this.value);
  }

}