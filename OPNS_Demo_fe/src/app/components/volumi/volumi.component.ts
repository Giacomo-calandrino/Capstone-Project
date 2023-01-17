import { Component, OnInit } from '@angular/core';
import { IScheda } from 'src/app/interfaces/ischeda';
import { SchedaService } from 'src/app/services/scheda.service';

@Component({
  selector: 'app-volumi',
  templateUrl: './volumi.component.html',
  styleUrls: ['./volumi.component.scss']
})
export class VolumiComponent implements OnInit {
  
  volumeProva:number = 1
  titoloProva:string= `Romance Dawn, l'alba di una grande avventura`  
  testoProva:string = `Monkey D. Luffy, chiamato Rufy, un bambino di sette anni, un giorno mangia il frutto del diavolo Gomu Gomu che gli fa diventare il corpo di gomma, ma che lo rende incapace di nuotare. In seguito a una peripezia col bandito Higuma, Rufy finisce in mare, ma viene salvato dal suo amico pirata Shanks, che perde, però, il braccio sinistro. Rufy gli promette allora di diventare il Re dei pirati guadagnandosi come regalo il cappello di paglia dell'amico. Dieci anni dopo infatti, Rufy si imbarca per mare con l'obbiettivo di diventare il Re dei pirati alla ricerca del leggendario tesoro lasciato da Gold Roger, il One Piece. Dopo essere naufragato e sconfitto la piratessa Albida, parte insieme al suo mozzo Kobi per l'isola seguente. Qui è imprigionato Roronoa Zoro, un famoso spadaccino e cacciatore di taglie, torturato da Hermeppo, il figlio del capitano della Marina Morgan mano d'ascia. Dopo la sconfitta di Morgan, Zoro entra nell'equipaggio di Rufy, e Kobi nella Marina. Rufy viene trasportato da un uccello su un'isola dove la ladra Nami era inseguita da alcuni pirati perché aveva rubato la mappa della Rotta Maggiore. Rufy sconfigge i tre pirati e Nami gli propone di lavorare insieme.`
  imgUrlProva:string = 'volume1.jpg'

  schede!:IScheda[]
  schedaDetails!:IScheda
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

  getLocalVolume():number{
    let utente:any = localStorage.getItem('volume')
    return this.localVolume = JSON.parse(utente)
  }

}
