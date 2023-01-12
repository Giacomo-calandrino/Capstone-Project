import { HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  loginError:boolean = false
  username:string = ''

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
    if(this.isUserLogged()){
      this.getUsername()
    }
  }

  login(){
    this.userService.login(this.loginData).subscribe((res:ILoginResponse) => {
      if(HttpStatusCode.Ok){
        this.userService.saveLoggedUser(res.username, res.volume, res.token)
        location.reload()
      }
    },() => {this.loginError = true})
  }

  signUp(){
    this.userService.signUp(this.user).subscribe((res:IUser) => {
      if(HttpStatusCode.Created){
        console.log(res)
        // LOGICA PER LOGIN AUTOMATICO
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

  getUsername() {
    let utente:any = localStorage.getItem('username');
    this.username = JSON.parse(utente);
  }

}
