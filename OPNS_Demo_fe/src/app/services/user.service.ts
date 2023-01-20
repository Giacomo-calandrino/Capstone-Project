import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ILogin, ILoginResponse } from '../interfaces/ilogin';
import { IPatch } from '../interfaces/ipatch';
import { IUser } from '../interfaces/iuser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  registerUrl:string = 'http://localhost:8080/api/users'
  loginUrl:string = 'http://localhost:8080/auth/login'
  
  jwtHelper = new JwtHelperService()

  constructor(private http:HttpClient) { }

  signUp(user:IUser){
    return this.http.post<IUser>(this.registerUrl, user)
  }

  login(loginData:ILogin){
    return this.http.post<ILoginResponse>(this.loginUrl, loginData)
  }

  saveLoggedUser(id:number ,username:string, volume:number, token:string){
    localStorage.setItem('id', JSON.stringify(id))
    localStorage.setItem('username', JSON.stringify(username))
    localStorage.setItem('volume', JSON.stringify(volume))
    localStorage.setItem('token', token)
  }

  patchVolume(patchUrl:string, patchData:IPatch){
    return this.http.patch<IUser>(patchUrl, patchData)
  }

  logout(){    
    localStorage.removeItem('id')
    localStorage.removeItem('username')
    localStorage.removeItem('volume')
    localStorage.removeItem('token')
  }

  isUserLogged(){
    if (localStorage.getItem('token') != null){
      return !this.jwtHelper.isTokenExpired(localStorage.getItem('token')!)
    }
    return false
  }

}
