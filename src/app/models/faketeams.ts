import {Team} from './Team.model';

export const fakeTeams:Team[]=[
    {team_id:1, team_name: "Rhinos", steps:15000},
    {team_id:2, team_name:"Microtopers", steps:50000, coins:105},
    {team_id:3, team_name:""},
    {team_id:-1, team_name:"Negative Nancies"},
    {team_id:500, team_name:null}
]