import { Player } from './Player.model';
import {IPreviewable} from "./IPreviewable";

export class Team implements IPreviewable {
    id:number;
    name?:string;

    steps?:number;
    coins?:number;

    players?:Player[];
}