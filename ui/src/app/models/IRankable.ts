export interface IRankable {
  coins?: number;
  steps?: number;
}

export abstract class Rankable implements IRankable {
  public abstract coins?: number;
  public abstract steps?: number;

}
