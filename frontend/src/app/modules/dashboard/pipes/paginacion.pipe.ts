import { Pipe, PipeTransform } from '@angular/core';
import { Gasto } from '../model/gasto';

@Pipe({
  name: 'paginacion'
})
export class PaginacionPipe implements PipeTransform {

  transform(lista: Gasto[], page:number=0,orden:string="recientes"): Gasto[] {
    
    if (orden=="recientes"){
      return lista.slice(page,page+5);
    } else if (orden=="mayores"){
        const listaFiltrada = lista.sort((a,b)=>b.importe-a.importe);
    } else if ("menores"){
        const listaFiltrada = lista.sort((a,b)=>a.importe-b.importe);
    } else {
       return lista
    }
    return lista.slice(page,page+5); 

    
    /*
    switch (orden){
      case "recientes":
        console.log("hola")
        break;
      case "antiguos":
        console.log("chhau")
        break;
      case "mayorPrecio":
        console.log("mayores")
        break;
      case "menorPrecio":
        console.log("menor precio")
        break;
      default:break
    }*/
  }

}
