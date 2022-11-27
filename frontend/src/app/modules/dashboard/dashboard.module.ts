import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GeneralComponent } from './pages/general/general.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { RouterModule } from '@angular/router';
import { LayoutCoreModule } from 'src/app/core/layouts/layout-dashboard/layout-core.module';
import { GastosComponent } from './pages/gastos/gastos.component';
import { IngresosComponent } from './pages/ingresos/ingresos.component';
import { EstadisticasComponent } from './pages/estadisticas/estadisticas.component';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';
import { EditarComponent } from './botones/editar/editar.component';
import { BorrarComponent } from './botones/borrar/borrar.component';
import { CommonModule } from '@angular/common';
import { DropComponent } from './componentes/drop/drop.component';
import { OrdenarComponent } from './componentes/ordenar/ordenar.component';
import { FiltrarComponent } from './componentes/filtrar/filtrar.component';
import { FiltrarIngresoComponent } from './componentes/filtrar-ingreso/filtrar-ingreso.component';
import { PaginacionPipe } from './pipes/paginacion.pipe';

@NgModule({
  declarations: [
    GeneralComponent,
    GastosComponent,
    IngresosComponent,
    EstadisticasComponent,
    ConfiguracionComponent,
    EditarComponent,
    BorrarComponent,
    DropComponent,
    OrdenarComponent,
    FiltrarComponent,
    FiltrarIngresoComponent,
    PaginacionPipe
  ],
  imports: [
    DashboardRoutingModule,
    NgbModule,
    RouterModule,
    LayoutCoreModule,
    CommonModule,
    FormsModule
  ],
  exports: [
  ]
})
export class DashboardModule { }
