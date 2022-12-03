import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gasto } from '../model/gasto';
import { environment } from 'src/environments/environment';
import { ResGastos } from '../model/res-gastos';

@Injectable({
  providedIn: 'any'
})
export class GastosService {

  constructor(private http:HttpClient) { }

  URL = environment.baseUrl + "expenses/";
  
  //OBTENER GASTOS
  public obtenerGastos():Observable<any>{
    return this.http.get<any>(this.URL + "user")    
  }

  //TODO:Buscar metodo
  //BUSCAR POR ID
  public buscarGasto(id:number):Observable<Gasto>{
    return this.http.get<Gasto>(this.URL + `buscar/${id}`)
  }

  //CREAR
  public guardarGasto(gasto:any):Observable<any>{
    console.log("El Servicio CREAR GASTO esta corriendo");
    console.log(this.URL + "save")
    return this.http.post<any>(this.URL + "save",gasto)
  }

   //TODO:Buscar metodo
  //ACTUALIZAR
  public actualizarGasto(id:number,gasto:any,headers:string):Observable<any>{    
  return this.http.put<any>(this.URL + `update/${id}`,gasto,{})
  }

  //BORRAR
  public borrarGasto(id:number):Observable<any>{
    console.log("El servicio de eliminado esta corriendo");
    console.log(this.URL + `delete/${id}`)
    return this.http.delete<any>(this.URL + `delete/${id}`)
  }

}