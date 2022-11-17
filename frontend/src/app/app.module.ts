import { NgModule } from '@angular/core';
import { BrowserModule }from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LandingComponent } from './components/landing/landing.component';import { GastosComponent } from './components/gastos/gastos.component';
import { DashboardModule } from './modules/dashboard/dashboard.module';

import { CreatorsComponent } from './components/landing/creators/creators.component';

import { AuthModule } from './modules/auth/auth.module';

import { HttpClientModule, /*HTTP_INTERCEPTORS*/ } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LandingComponent,
    GastosComponent
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
  providers: [/*interceptorProvider*/],
  bootstrap: [AppComponent]
})
export class AppModule { }
