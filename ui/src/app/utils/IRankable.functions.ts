import {IRankable} from "../models/IRankable"

export function compare(first: IRankable, second: IRankable): number
{
    const coinCompare = compareCoins(first,second);
    return coinCompare==0 ?  compareSteps(first,second) : coinCompare
}

function compareCoins(first: IRankable, second: IRankable): number {
    if(!first.coins && !second.coins)
        return 0;
    if(first.coins && ! second.coins)
        return -1;
    if(second.coins && ! first.coins)
        return 1;
    
    if(first.coins>second.coins)
        return -1;
    if(second.coins>first.coins)
        return 1;
    return 0;
}


function compareSteps(first: IRankable, second: IRankable): number {
    if(!first.steps && !second.steps)
        return 0;
    if(first.steps && ! second.steps)
        return -1;
    if(second.steps && ! first.steps)
        return 1;
    if(first.steps>second.steps)
        return -1;
    if(second.steps>first.steps)
        return 1;
    return 0;
}

export function topN(rankables : IRankable[], n:number):IRankable[]{
    return rankables
        .slice() // Creates a working copy 
        .sort((a,b)=>compare(a,b)) //Sorts with compare function from above
        .slice(0,n);//Takes top n
}