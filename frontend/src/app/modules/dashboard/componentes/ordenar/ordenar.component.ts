import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { runInThisContext } from 'vm';

@Component({
  selector: 'app-ordenar',
  templateUrl: './ordenar.component.html',
  styleUrls: ['./ordenar.component.css']
})
export class OrdenarComponent implements OnInit {

  @Output() mensaje = new EventEmitter<string>();
  palabra:string="";

  constructor() { }

  ngOnInit(): void {
  }

  masRecientes(){
    this.mensaje.emit("recientes");
  }
  masAntiguos(){
    this.mensaje.emit("antiguos");
  }
  mayorImporte(){
    this.mensaje.emit("mayores");
  }
  menorImporte(){
    this.mensaje.emit("menores");
  }

}
