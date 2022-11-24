import { Component, OnInit } from '@angular/core';
import { FechaService } from '../../services/fecha.service';

@Component({
  selector: 'app-gastos',
  templateUrl: './gastos.component.html',
  styleUrls: ['./gastos.component.css']
})
export class GastosComponent implements OnInit {

  fecha:any;

  constructor(private fechaService: FechaService) { }

  ngOnInit(): void {/*
    const date = new Date();
    const mes = date.getMonth()+1;
    const año = date.getFullYear();
    const dia = date.getDate()
    console.log(dia);
    console.log(año);
    console.log(mes);
    const diaActual = año+"-"+mes+"-"+dia;
    console.log(diaActual);
    this.fecha = diaActual;*/
    this.fecha = this.fechaService.actual()
  }

  guardarGasto(){
    console.log("okey")
  }

}
