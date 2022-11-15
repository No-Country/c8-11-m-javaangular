import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateDashboardComponent } from 'src/app/core/layouts/layout-dashboard/template-dashboard/template-dashboard.component';

import { ExpensesComponent } from './pages/expenses/expenses.component';
import { GeneralComponent } from './pages/general/general.component';
import { RevenueComponent } from './pages/revenue/revenue.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { StatsComponent } from './pages/stats/stats.component';

// Components Page


const routes: Routes = [
  {
    path: '',
    component: TemplateDashboardComponent,
    children: [
      { path: 'general', component: GeneralComponent },
      { path: 'expenses', component: ExpensesComponent },
      { path: 'revenue', component: RevenueComponent },
      { path: 'stats', component: StatsComponent },
      { path: 'settings', component: SettingsComponent },
      { path: '**', redirectTo: 'general'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
