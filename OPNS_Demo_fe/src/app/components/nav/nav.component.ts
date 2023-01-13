import { HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
  
  user:IUser = {
    nome: '',
    cognome: '',
    email: '',
    username: '',
    password: '',
    volume: ''
  }
  
  loginData:ILogin = {
    username: '',
    password:''
  }

  patchData:IPatch = {
    volume: ''
  }
  
  loginError:boolean = false
  patchSuccess:boolean = false
  
  id:number = this.getId()
  username:string = this.getUsername()
  

  patchUrl:string = `http://localhost:8080/api/users/${this.id}`
  
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    if(this.isUserLogged()){      
      this.getVolume()
    }
  }

  signUp(){
    this.userService.signUp(this.user).subscribe((res:IUser) => {
      if(HttpStatusCode.Created){
        console.log(res)
        // LOGICA PER LOGIN AUTOMATICO
      }
    })
  }

  login(){
    this.userService.login(this.loginData).subscribe((res:ILoginResponse) => {
      if(HttpStatusCode.Ok){
        this.userService.saveLoggedUser(res.id ,res.username, res.volume, res.token)
        location.reload()
      }
    },() => {this.loginError = true})
  }

  patchVolume(){
    this.userService.patchVolume(this.patchUrl, this.patchData).subscribe((res:IUser) => {
      if(HttpStatusCode.Ok){
        localStorage.setItem('volume', JSON.stringify(res.volume))
        this.patchSuccess = true
      }
    })
  }

  logout(){
    this.userService.logout()
    this.router.navigate(['']).then(() => {
      location.reload()
    })
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

  getVolume(){
    let utente:any = localStorage.getItem('volume')
    this.patchData.volume = JSON.parse(utente)
  }

  resetLoginError(){
    this.loginError = false
  }

  resetPatchSuccess(){
    this.patchSuccess = false
  }

}
