import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Gasto } from '../../model/gasto';
import { Prueba } from '../../model/prueba';
import { ResGastos } from '../../model/res-gastos';
import { ResPrueba } from '../../model/resPrueba';
import { PruebaService } from '../../services/prueba.service';

@Component({
  selector: 'app-prueba',
  templateUrl: './prueba.component.html',
  styleUrls: ['./prueba.component.css']
})
export class PruebaComponent implements OnInit {

  listaGastos:Gasto[]=[];
  id:number=0;
  dato:any;
  respuesta:any;
  listaGastos$:any;

  constructor(private pruebaService:PruebaService, private router:Router) {    
   }

  ngOnInit(): void {
    this.pruebaService.obtenerGastos().subscribe(
      data => {
        this.respuesta = data;     
        console.log(this.respuesta);
        this.listaGastos=this.respuesta.response;
        console.log(this.listaGastos)
      },
      err => {
        alert("Algo ha fallado");
        console.error("Los datos del servidor no llegan");
        console.log(err);
      })    
  }

  editableId(id:number,dato:any){
    console.log(id);
    console.log(dato)
  }
  trashId(id:number){
    console.log(id)
  }
}
  

