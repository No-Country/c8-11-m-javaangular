import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gasto } from '../model/gasto';

@Injectable({
  providedIn: 'root'
})
export class GastosService {

  constructor(private http:HttpClient) { }

  gastoURL = "http://localhost:8080/gasto/";
  
  //OBTENER DATOS
  public obtenerGastos():Observable<Gasto[]>{
    return this.http.get<Gasto[]>(this.gastoURL + "ver")    
  }

  //BUSCAR POR ID
  public buscarGasto(id:number):Observable<Gasto>{
    return this.http.get<Gasto>(this.gastoURL + `buscar/${id}`)
  }

  //CREAR
  public guardarGasto(gasto:Gasto):Observable<any>{
    return this.http.post<any>(this.gastoURL + "guardar",gasto)
  }

  //ACTUALIZAR
  public actualizarGasto(id:number,gasto:Gasto,httpHeaders:HttpHeaders):Observable<any>{    
  return this.http.put<any>(this.gastoURL + `editar/${id}`,gasto,{})
  }

  //BORRAR
  public borrarGasto(id:number):Observable<any>{
    return this.http.delete<any>(this.gastoURL + `borrar/${id}`)
  }

}