import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { GeneralComponent } from './pages/general/general.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { ExpensesComponent } from './pages/expenses/expenses.component';
import { RevenueComponent } from './pages/revenue/revenue.component';
import { StatsComponent } from './pages/stats/stats.component';
import { SettingsComponent } from './pages/settings/settings.component';
import { RouterModule } from '@angular/router';
import { LayoutCoreModule } from 'src/app/core/layouts/layout-dashboard/layout-core.module';
@NgModule({
  declarations: [
    GeneralComponent,
    ExpensesComponent,
    RevenueComponent,
    StatsComponent,
    SettingsComponent,
  ],
  imports: [
    DashboardRoutingModule,
    NgbModule,
    RouterModule,
    LayoutCoreModule
  ],
  exports: [
  ]
})
export class DashboardModule { }
