import { Component, OnInit } from '@angular/core';
import { IScheda } from 'src/app/interfaces/ischeda';
import { SchedaService } from 'src/app/services/scheda.service';

@Component({
  selector: 'app-volumi',
  templateUrl: './volumi.component.html',
  styleUrls: ['./volumi.component.scss']
})
export class VolumiComponent implements OnInit {  

  schede!:IScheda[]
  schedaDetails:IScheda = {
    id: '',
    volume: '',
    titolo: '',
    testo: '',
    imgUrl: 'volume1.jpg'
  }

  searchText:string = ''

  isLoading:boolean = false

  localVolume:number = this.getLocalVolume()

  constructor(private schedaService:SchedaService) { }

  ngOnInit(): void {
    this.getSchede()
  }

  getSchede(){
    this.isLoading = true
    this.schedaService.getSchede(this.localVolume).subscribe((res:IScheda[]) => {
      this.schede = res      
      this.isLoading = false
    })
  }

  getSchedaDetails(id:number|string){
    this.schedaService.getSchedaDetails(id).subscribe((res:IScheda) => {
      this.schedaDetails = res
    })
  }

  getLocalVolume():number{
    let utente:any = localStorage.getItem('volume')
    return this.localVolume = JSON.parse(utente)
  }

  onSearchTextEntered(searchValue:string){
    this.searchText = searchValue
  }

}
