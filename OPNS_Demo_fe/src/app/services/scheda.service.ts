import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IScheda } from '../interfaces/ischeda';

@Injectable({
  providedIn: 'root'
})
export class SchedaService {

  schedeNoSpoilerUrl:string = `http://localhost:8080/api/schede/noSpoiler`
  schedaDetailsUrl:string = 'http://localhost:8080/api/schede'

  constructor(private http:HttpClient) { }

  getSchede(volume:number){
    return this.http.get<IScheda[]>(this.schedeNoSpoilerUrl + '/' + volume)
  }

  getSchedaDetails(id:number|string){
    return this.http.get<IScheda>(this.schedaDetailsUrl + '/' + id)
  }

}
