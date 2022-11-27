import { Pipe, PipeTransform } from '@angular/core';
import { Ingreso } from '../model/ingreso';

@Pipe({
  name: 'paginacionIngreso'
})
export class PaginacionIngresoPipe implements PipeTransform {

  transform(lista2Ingresos: Ingreso[], page:number=0): Ingreso[] {
    return lista2Ingresos.slice(page,page+5);
  }

}
