import { Pipe, PipeTransform } from '@angular/core';
import { Ingreso } from '../model/ingreso';

@Pipe({
  name: 'paginacionIngreso'
})
export class PaginacionIngresoPipe implements PipeTransform {

  transform(lista: Ingreso[], page:number=0,orden:string="recientes"): Ingreso[] {
    
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

    
  }

}
