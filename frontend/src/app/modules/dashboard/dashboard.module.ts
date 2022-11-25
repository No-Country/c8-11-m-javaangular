import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
import { DropComponent } from './componentes/drop/drop.component'

@NgModule({
  declarations: [
    GeneralComponent,
    GastosComponent,
    IngresosComponent,
    EstadisticasComponent,
    ConfiguracionComponent,
    EditarComponent,
    BorrarComponent,
    DropComponent
  ],
  imports: [
    DashboardRoutingModule,
    NgbModule,
    RouterModule,
    LayoutCoreModule,
    CommonModule
  ],
  exports: [
  ]
})
export class DashboardModule { }
