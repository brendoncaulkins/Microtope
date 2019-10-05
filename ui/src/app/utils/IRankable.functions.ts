import {IRankable} from '../models/IRankable';

export function compare(first: IRankable, second: IRankable): number {
  const coinCompare = compareCoins(first, second);
  return coinCompare === 0 ? compareSteps(first, second) : coinCompare;
}

function compareRankables(
  first: IRankable,
  second: IRankable,
  propToCompare: keyof IRankable
): number {
  const fVal = first[propToCompare];
  const sVal = second[propToCompare];

  // if one arg has invalid prop
  if (!fVal || !sVal) {
    return !!fVal ? -1 : !!sVal ? 1 : 0;
  }

  return !(fVal - sVal) ? 0 : fVal > sVal ? -1 : 1;
}

function compareCoins(first: IRankable, second: IRankable): number {
  return compareRankables(first, second, 'coins');
}

function compareSteps(first: IRankable, second: IRankable): number {
  return compareRankables(first, second, 'steps');
}

export const topNCurry = (n: number) =>
  (rankables: IRankable[]) =>
    rankables.slice()
             .sort((a, b) => compare(a, b))
             .slice(0, n);
