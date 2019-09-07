import { Predicate } from '@angular/core';

import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';

import {IPreviewable} from "../models/IPreviewable";

export abstract class IPreviewableService<T extends IPreviewable> {

  abstract getAll():Observable<T[]>;

  public getByID(id: number): Observable<T> {
    return this.filterByID(this.getAll(),id);
  }

  public getByName(name: string): Observable<T> {
    return this.filterByName(this.getAll(),name);
  }

  private filterByName(toFilter:Observable<T[]>, name:string):Observable<T>{
    return this.filterByPredicate(toFilter, (p=> p.name===name));
  }
  private filterByID(toFilter:Observable<T[]>, id:number):Observable<T>{
    return this.filterByPredicate(toFilter, (p=> p.id===id));
  }

  private filterByPredicate<T>(toFilter:Observable<T[]>, predicate:Predicate<T>){
      return toFilter.pipe(
          map(obs => obs.filter(p=>predicate(p))),
          map(obs => obs[0])
          );
  }

}
