import {IRankable} from "../models/IRankable"

export function compare(first: IRankable, second: IRankable): number
{
    if (second.coins && first.coins) {
      if (second.coins > first.coins) {
        return -1;
      } else if (second.coins < first.coins) {
        return 1;
      } else if (second.steps && first.steps) {
        if (second.steps > first.steps) {
          return -1;
        } else if (second.steps < first.steps) {
          return 1;
        } else {
          return 0;
        }
      } else {
        return 0;
      }
    }
}

export function topN(rankables : IRankable[], n:number):IRankable[]{
    return rankables
        .slice() // Creates a working copy 
        .sort((a,b)=>compare(a,b)) //Sorts with compare function from above
        .slice(0,n);//Takes top n
}