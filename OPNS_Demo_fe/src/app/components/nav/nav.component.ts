import { HttpStatusCode } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
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

  username:string = ''
  loginError:boolean = false

  constructor(private userService:UserService, private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.userService.login(this.loginData).subscribe((res:ILoginResponse) => {
      if(HttpStatusCode.Ok){
        this.userService.saveLoggedUser(res.username, res.volume, res.token)
        location.reload()
      }else{this.loginError = true}
    })
  }

  signUp(){
    this.userService.signUp(this.user).subscribe((res:IUser) => {
      if(HttpStatusCode.Created){
        Swal.fire(
          'Registrazione completata con successo',
          `Benvenuto a bordo ${res.username}!!`,
          'success'
        )
      }
    })
  }

  logout(){
    this.userService.logout()
    
    this.user = {
      nome: '',
      cognome: '',
      email: '',
      username: '',
      password: '',
      volume: ''
    }
    
    this.loginData = {
      username: '',
      password: '',
    }

    this.router.navigate([''])
  }

  isUserLogged() {
    return this.userService.isUserLogged()
  }

  getUsername() {
    let utente:any = localStorage.getItem('username');
    this.username = JSON.parse(utente);
  }

}
