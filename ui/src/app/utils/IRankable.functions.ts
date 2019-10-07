import {IRankable} from '../models/IRankable';
import { Observable } from 'rxjs';
import { take, map, flatMap } from 'rxjs/operators';
import { ɵisListLikeIterable } from '@angular/core';

export function compare(first: IRankable, second: IRankable): number {
  const coinCompare = compareCoins(first, second);
  return coinCompare === 0 ? compareSteps(first, second) : coinCompare;
}


// Returns the first n IRankables in the Observable inputted
// Per Rxjs logic, this Observable is a fresh new copy, and does not alter the initial Observable
export function topN(input:  Observable<IRankable[]>, n:number) : Observable<IRankable[]> {
  return input.pipe(
      map(list => list.slice()),
      map(iRankList => iRankList.sort((a,b) => compare(a,b))),
      map(sortedIRankList => sortedIRankList.slice(0,n))
    )
}

function compareRankables(
  first: IRankable,
  second: IRankable,
  propToCompare: keyof IRankable
): number {
  const firstVal = first[propToCompare];
  const secondVal = second[propToCompare];

  // if one arg has invalid prop
  if (!firstVal || !secondVal) {
    return !!firstVal ? -1 : !!secondVal ? 1 : 0;
  }

  return !(firstVal - secondVal) ? 0 : firstVal > secondVal ? -1 : 1;
}

function compareCoins(first: IRankable, second: IRankable): number {
  return compareRankables(first, second, 'coins');
}

function compareSteps(first: IRankable, second: IRankable): number {
  return compareRankables(first, second, 'steps');
}

