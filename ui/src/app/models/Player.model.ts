export interface IPlayer {
    player_id : number;
}

export class Player implements IPlayer{
    player_id : number;
    player_name?:string;

    team_id?:number;
    team_name?:string;

    steps?:number;
    coins?:number;
}