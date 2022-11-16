import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LandingComponent } from './components/landing/landing.component';
import { LoginComponent } from './components/login/login.component';
import { RegistroComponent } from './components/registro/registro.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { GastosComponent } from './components/gastos/gastos.component';
import { DashboardModule } from './modules/dashboard/dashboard.module';

import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    LoginComponent,
    RegistroComponent,
<<<<<<< HEAD
    DashboardComponent,
    GastosComponent,
    
=======
    GastosComponent
>>>>>>> 394139eaada58b8e48a00459db1ebaf733a93311
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
<<<<<<< HEAD
    NgbModule,
    ReactiveFormsModule
=======
    DashboardModule,
    NgbModule
>>>>>>> 394139eaada58b8e48a00459db1ebaf733a93311
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
