import {IPreviewable} from "./IPreviewable";
export class Player implements IPreviewable{
    id : number;
    name?:string;

    team_id?:number;
    team_name?:string;

    steps?:number;
    coins?:number;
}