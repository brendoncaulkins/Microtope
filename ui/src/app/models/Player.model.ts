import {IPreviewable} from "./IPreviewable";
import {IRankable,Rankable} from "./IRankable";
export class Player extends Rankable implements IPreviewable {
    id : number;
    name?:string;

    team_id?:number;
    team_name?:string;

    steps?:number;
    coins?:number;
}