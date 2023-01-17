import { HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { IPatch } from 'src/app/interfaces/ipatch';
import { UserService } from 'src/app/services/user.service';
import { ILogin, ILoginResponse } from '../../interfaces/ilogin';
import { IUser } from '../../interfaces/iuser';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  
  signUpData:IUser = {
    nome: '',
    cognome: '',
    email: '',
    username: '',
    password: '',
    volume: ''
  }
  
  loginData:ILogin = {
    username: '',
    password: ''
  }

  patchData:IPatch = {
    volume: ''
  }
  
  signUpSuccess:boolean = false
  existingUsername:boolean = false
  loginError:boolean = false
  patchError:boolean = false
  
  id:number = this.getId()
  username:string = this.getUsername()
  volume:number = this.getVolume()

  patchUrl:string = `http://localhost:8080/api/users/${this.id}`
  
  constructor(private userService:UserService) { }

  ngOnInit():void{}

  signUp(){
    this.userService.signUp(this.signUpData).subscribe((res:IUser) => {
      if(HttpStatusCode.Created){
        this.signUpSuccess = true
      }
    },() => {this.existingUsername = true})
  }

  login(){
    this.userService.login(this.loginData).subscribe((res:ILoginResponse) => {
      if(HttpStatusCode.Ok){
        this.userService.saveLoggedUser(res.id, res.username, res.volume, res.token)
        location.reload()
      }
    },() => {this.loginError = true})
  }

  patchVolume(){
    this.userService.patchVolume(this.patchUrl, this.patchData).subscribe((res:IUser) => {
      if(this.patchData.volume != this.volume){
        if(HttpStatusCode.Ok){
          localStorage.setItem('volume', JSON.stringify(res.volume))
          location.reload()
        }
      }else{this.patchError = true}
    })
  }

  logout(){
    this.userService.logout()
  }

  isUserLogged() {
    return this.userService.isUserLogged()
  }

  getId():number{
    let utente:any = localStorage.getItem('id')
    return this.id = JSON.parse(utente)
  }

  getUsername():string{
    let utente:any = localStorage.getItem('username')
    return this.username = JSON.parse(utente)
  }

  getVolume():number{
    let utente:any = localStorage.getItem('volume')
    return this.patchData.volume = JSON.parse(utente)
  }

  resetSignUpAlerts(){
    this.signUpSuccess = false
    this.existingUsername = false
  }

  resetLoginError(){
    this.loginError = false
  }

  resetPatchError(){
    this.patchError = false
  }

}
