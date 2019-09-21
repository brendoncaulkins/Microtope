import { Player } from './Player.model';
import {IPreviewable} from './IPreviewable';
import {IRankable} from './IRankable';
export class Team implements IPreviewable, IRankable {
    id: number;
    name?: string;

    steps?: number;
    coins?: number;

    players?: Player[];
}
