import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IScheda } from '../interfaces/ischeda';

@Injectable({
  providedIn: 'root'
})
export class SchedaService {

  schedeUrl:string = `http://localhost:8080/api/schede/limit`
  schedaDetailsUrl:string = 'http://localhost:8080/api/schede'

  constructor(private http:HttpClient) { }

  getSchede(volume:number){
    return this.http.get<IScheda[]>(this.schedeUrl + '/' + volume)
  }

  getSchedaDetails(id:number){
    return this.http.get<IScheda>(this.schedaDetailsUrl + '/' + id)
  }

}
