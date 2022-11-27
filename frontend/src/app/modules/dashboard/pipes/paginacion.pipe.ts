import { Pipe, PipeTransform } from '@angular/core';
import { Gasto } from '../model/gasto';

@Pipe({
  name: 'paginacion'
})
export class PaginacionPipe implements PipeTransform {

  transform(lista2Gastos: Gasto[], page:number=0): Gasto[] {
    return lista2Gastos.slice(page,page+5);
  }

}
