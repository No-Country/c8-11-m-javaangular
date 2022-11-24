import { Component, OnInit } from '@angular/core';
import { FechaService } from '../../services/fecha.service';

@Component({
  selector: 'app-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.css']
})
export class IngresosComponent implements OnInit {

  fecha:any;
  
  constructor(private fechaService: FechaService) { }

  ngOnInit(): void {
    this.fecha = this.fechaService.actual()
  }
  guardarIngreso(){
    console.log("Hola")

  }

}
