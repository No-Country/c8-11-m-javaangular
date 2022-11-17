import { NgModule } from '@angular/core';
import { RouterModule, Routes} from '@angular/router';

// Components Page
import { LandingComponent } from './components/landing/landing.component';

import { GastosComponent } from './components/gastos/gastos.component';
import { LoginComponent } from './modules/auth/pages/login/login.component';
import { RegistroComponent } from './modules/auth/pages/registro/registro.component';

const routes: Routes = [
  {path:'landing', component:LandingComponent},/*
  {path:'login', component:LoginComponent},
  {path:'registro', component:RegistroComponent},*/
  {path:'login', component:LoginComponent},
  {path:'registro', component:RegistroComponent},


  { path: 'dashboard', loadChildren: () => import('./modules/dashboard/dashboard.module').then(m => m.DashboardModule) },
  {path:'gastos', component:GastosComponent},
  { path: '**', redirectTo: 'landing'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
