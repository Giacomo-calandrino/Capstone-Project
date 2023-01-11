export interface ILogin {
    username:string;
    password:string
}

export interface ILoginResponse {
    token:string;
    type:string;
    id:number;
    username:string;
    volume:number;
    roles:[]
}
