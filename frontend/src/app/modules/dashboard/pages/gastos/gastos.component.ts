import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Gasto } from '../../model/gasto';
import { FechaService } from '../../services/fecha.service';
import { GastosService } from '../../services/gastos.service';

@Component({
  selector: 'app-gastos',
  templateUrl: './gastos.component.html',
  styleUrls: ['./gastos.component.css']
})
export class GastosComponent implements OnInit {

  fechaActual:any;
  active:boolean=true;
  listaGastos:Gasto[]=[];
  nuevoGasto:Gasto[]=[];
  newFecha:Date = new Date;
  newDescripcion:string="";
  newCategoria:string="";
  newImporte:number=0; 
  editId:number=0;
  borrarId:number=0; 

  constructor(private fechaService: FechaService,private gastoService:GastosService) {}
  

  ngOnInit(): void {
    this.fechaActual = this.fechaService.actual();
    this.obtenerGastos();
    this.newFecha = new Date();
  }

  // Obtener Gastos
  obtenerGastos(){
    this.gastoService.obtenerGastos().subscribe(data =>{
      this.listaGastos=data;
    });
  }

  openGasto(){
    this.fechaActual = this.fechaService.actual();
  }
  // Guardar Gasto  
  guardarGasto(){
    const nuevoGasto = {
      fecha:this.newFecha,
      categoria:this.newCategoria,
      descripcion:this.newCategoria,
      importe:this.newImporte
    }
    console.log(nuevoGasto)
    console.log(nuevoGasto.fecha)
    this.gastoService.guardarGasto(nuevoGasto).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{
        this.obtenerGastos();
        this.newDescripcion="Hola"
      })
  }

  /*--------EDITAR GASTO----------------------------------------------*/

  //Boton abrir modal: Capturar Id y experiencia
  editableId(id:any,gasto: Gasto){
    const editableGasto = gasto;
    this.editId = id;  
    
    /* Mostrar datos en el modal */
    this.newFecha = editableGasto.fecha;
    this.newCategoria = editableGasto.categoria;
    this.newDescripcion = editableGasto.descripcion;
    this.newImporte = editableGasto.importe;
  }

  //BOTON ACTUALIZAR EXPERIENCIA

  editarGasto(): void{
    const nuevoGasto = {
      fecha:this.newFecha,
      categoria:this.newCategoria,
      descripcion:this.newCategoria,
      importe:this.newImporte
    }    
    console.log(nuevoGasto)
    const editId = this.editId;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
      });

    this.gastoService.actualizarGasto(editId,nuevoGasto,headers).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerGastos()})
  }

  /*------BORRAR EDUCACION---------------------------------------------*/

  //BOTON abrir modal: Capturar Id y experiencia
  trashId(id:any,gasto:Gasto[]): void{
    this.borrarId = id;   
    console.log(this.borrarId);  
  }
  
  //BOTON ELIMINAR EDUCACION
  eliminarEducacion(): void{
    this.gastoService.borrarGasto(this.borrarId).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerGastos()})
  }


}
