import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.css']
})
export class IngresosComponent implements OnInit {

  num=0;
  constructor() { }

  ngOnInit(): void {
  }
  guardarIngreso(){
    console.log("Hola")

  }

}
