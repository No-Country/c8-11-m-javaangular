import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateDashboardComponent } from 'src/app/core/layouts/layout-dashboard/template-dashboard/template-dashboard.component';
import { ConfiguracionComponent } from './pages/configuracion/configuracion.component';
import { EstadisticasComponent } from './pages/estadisticas/estadisticas.component';


import { GastosComponent } from './pages/gastos/gastos.component';
import { GeneralComponent } from './pages/general/general.component';
import { IngresosComponent } from './pages/ingresos/ingresos.component';

// Components Page


const routes: Routes = [
  {
    path: '',
    component: TemplateDashboardComponent,
    children: [
      { path: 'gastos', component: GastosComponent },
      { path: 'general', component: GeneralComponent },
      { path: 'ingresos', component: IngresosComponent },
      { path: 'estadisticas', component: EstadisticasComponent },
      { path: 'configuracion', component: ConfiguracionComponent },/*
      { path: 'expenses', component: ExpensesComponent },
      { path: 'revenue', component: RevenueComponent },
      { path: 'stats', component: StatsComponent },
      { path: 'settings', component: SettingsComponent },*/
      { path: '**', redirectTo: 'general'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
