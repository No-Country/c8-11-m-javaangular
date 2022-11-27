import { Pipe, PipeTransform } from '@angular/core';
import { Gasto } from '../model/gasto';

@Pipe({
  name: 'paginacion'
})
export class PaginacionPipe implements PipeTransform {

  transform(lista: Gasto[], page:number=0): Gasto[] {
    return lista.slice(page,page+5);
  }

}
