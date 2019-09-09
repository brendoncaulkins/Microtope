export interface IRankable {
  coins?: number;
  steps?: number;
}

export abstract class Rankable implements IRankable {
  public abstract coins?: number;
  public abstract steps?: number;

  public compare(other: Rankable): number {
    if (other.coins && this.coins) {
      if (other.coins > this.coins) {
        return -1;
      } else if (other.coins < this.coins) {
        return 1;
      } else if (other.steps && this.steps) {
        if (other.steps > this.steps) {
          return -1;
        } else if (other.steps < this.steps) {
          return 1;
        } else {
          return 0;
        }
      } else {
        return 0;
      }
    }
  }

}
