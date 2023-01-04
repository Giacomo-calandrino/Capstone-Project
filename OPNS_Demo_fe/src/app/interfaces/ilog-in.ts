import { IUser } from "./iuser";

export interface ILogIn {
    username:string;
    password:string
}

export interface ILogInResponse {
    accesToken: string;
    user: IUser
}
