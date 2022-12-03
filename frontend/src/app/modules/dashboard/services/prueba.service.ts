import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PruebaService {

  constructor(private http:HttpClient) { }

  gastoURL = environment.baseUrl + "expenses/";
  
  //OBTENER GASTOS
  public obtenerGastos():Observable<any>{
    return this.http.get<any>(this.gastoURL + "user")    
  }
}
