import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ILogin, ILoginResponse } from '../interfaces/ilogin';
import { IUser } from '../interfaces/iuser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  registerUrl:string = 'http://localhost:8080/api/users';
  loginUrl:string = 'http://localhost:8080/auth/login';
  patchUrl:string = 'http://localhost:8080/api/users/';

  constructor(private http:HttpClient) { }

  signUp(user:IUser){
    return this.http.post<IUser>(this.registerUrl, user)
  }

  login(loginData:ILogin){
    return this.http.post<ILoginResponse>(this.loginUrl, loginData)
  }

  saveLoggedUser(username:string, volume:number, token:string){
    localStorage.setItem('username', JSON.stringify(username))
    localStorage.setItem('volume', JSON.stringify(volume))
    localStorage.setItem('token', token)
  }

  patchVolume(volume:number){
    return this.http.patch<IUser>(this.patchUrl, volume)
  }

  logout(){    
    localStorage.clear()
  }

  isUserLogged():boolean {
    return localStorage.getItem('token') != null
  }

}
