import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JwtDTO } from '../models/jwt-dto';
import { LoginUsuario } from '../models/login-usuario';
import { NuevoUsuario } from '../models/nuevo-usuario';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  URL = environment.baseUrl+"users/";

  constructor(private httpClient: HttpClient) { }

  public nuevo(nuevoUsuario: NuevoUsuario): Observable<any> {
    console.log("El servicio Registro esta corriendo en la URL:");
    console.log(this.URL+'register');
    return this.httpClient.post<any>(this.URL + 'register', nuevoUsuario);
  }

  public login(loginUsuario: LoginUsuario,httpHeaders:HttpHeaders): Observable<any>{
    console.log("El servicio login esta corriendo en la URL");
    console.log(this.URL+'login');
    return this.httpClient.post<any>(this.URL + 'login/',loginUsuario)    
  }
    
  
}
