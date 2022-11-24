import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FechaService {

  constructor() { }

  public actual(){
    const date = new Date();
    const mes = date.getMonth()+1;
    const año = date.getFullYear();
    const dia = date.getDate();
    const diaActual = año+"-"+mes+"-"+dia;
    return diaActual
  }
}
