import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingreso } from '../model/ingreso';

@Injectable({
  providedIn: 'root'
})
export class IngresosService {

  constructor(private http:HttpClient) { }

  URL = "http://localhost:8080/ingreso/";
  
  //OBTENER DATOS
  public obtenerIngresos():Observable<Ingreso[]>{
    return this.http.get<Ingreso[]>(this.URL + "ver")    
  }

  //BUSCAR POR ID
  public buscarIngreso(id:number):Observable<Ingreso>{
    return this.http.get<Ingreso>(this.URL + `buscar/${id}`)
  }

  //CREAR
  public guardarIngreso(ingreso:Ingreso):Observable<any>{
    return this.http.post<any>(this.URL + "guardar",ingreso)
  }

  //ACTUALIZAR
  public actualizarIngreso(id:number,ingreso:Ingreso,httpHeaders:HttpHeaders):Observable<any>{    
  return this.http.put<any>(this.URL + `editar/${id}`,ingreso,{})
  }

  //BORRAR
  public borrarIngreso(id:number):Observable<any>{
    return this.http.delete<any>(this.URL + `borrar/${id}`)
  }

}