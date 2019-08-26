import { IPlayer } from './Player.model';

export interface ITeam {
    team_id:number;
    team_name:string;
}

export class Team implements ITeam {
    team_id:number;
    team_name:string;

    steps?:number;
    coins?:number;

    players?:IPlayer;
}