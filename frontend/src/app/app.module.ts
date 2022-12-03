import { NgModule } from '@angular/core';
import { BrowserModule }from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// Components
import { LandingComponent } from './components/landing/landing.component';
import { GastosComponent } from './components/gastos/gastos.component';
import { CreatorsComponent } from './components/landing/creators/creators.component';

// Modules
import { AuthModule } from './modules/auth/auth.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { interceptorProvider } from './interceptors/interceptor.service';
import { HojaComponent } from './components/hoja/hoja.component';
import { GastosService } from './modules/dashboard/services/gastos.service';


@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    GastosComponent,
    HojaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    DashboardModule,
    AuthModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [interceptorProvider, GastosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
