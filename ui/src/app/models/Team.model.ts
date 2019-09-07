import { Player } from './Player.model';
import {IPreviewable} from "./IPreviewable";
export interface ITeam {
    team_id:number;
    team_name:string;
}

export class Team implements IPreviewable {
    id:number;
    name?:string;

    steps?:number;
    coins?:number;

    players?:Player[];
}