import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { TemplateDashboardComponent } from './template-dashboard/template-dashboard.component';
import { MenuDashboardComponent } from './menu-dashboard/menu-dashboard.component';

@NgModule({
  declarations: [
    MenuDashboardComponent,
    TemplateDashboardComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
  ],
  exports: [
    MenuDashboardComponent,
    TemplateDashboardComponent
  ]
})
export class LayoutCoreModule { }
