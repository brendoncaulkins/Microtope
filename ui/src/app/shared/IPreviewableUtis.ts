import {IPreviewable} from "../models/IPreviewable";
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { Predicate } from '@angular/core';

export function filterByID(toFilter:Observable<IPreviewable[]>, id:number):Observable<IPreviewable>{
    return filterByPredicate(toFilter, (p=> p.id===id));
}

export function filterByName(toFilter:Observable<IPreviewable[]>, name:string):Observable<IPreviewable>{
    return filterByPredicate(toFilter, (p=> p.name===name));
}

function filterByPredicate<IPreviewable>(toFilter:Observable<IPreviewable[]>, predicate:Predicate<IPreviewable>){
    return toFilter.pipe(
        map(obs => obs.filter(p=>predicate(p))),
        map(obs => obs[0])
      );
}