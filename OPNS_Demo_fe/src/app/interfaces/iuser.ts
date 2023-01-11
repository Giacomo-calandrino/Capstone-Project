export interface IUser {
    id?:number;
    nome:string;
    cognome:string;
    email:string;
    username:string;
    password:string;
    volume:number | string
    roles?:[]
}
