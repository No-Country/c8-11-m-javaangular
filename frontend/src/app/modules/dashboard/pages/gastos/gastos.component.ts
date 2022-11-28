import { HttpHeaders } from '@angular/common/http';
import { Component, OnChanges, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
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
  newFecha:Date=new Date();
  newDescripcion:string="";
  newCategoria:string="";
  newImporte!:number; 
  editId:number=0;
  borrarId:number=0;
  
  // Paginación
  page:number=0;
  orden:string="";

  lista:Gasto[]=[];
  datos:any;
  form:FormGroup | undefined;
  coco:boolean=true;
/*
  lista2Gastos = [
    {
        fecha:'11-12-1980',
        categoria:'Servicios',
        descripcion:'Electricidad',
        importe:3000
    },
    {
        fecha:'11-12-1980',
        categoria:'Alimentos',
        descripcion:'Verduleria',
        importe:5000
    },
    {
        fecha:'11-12-1980',
        categoria:'Movilidad',
        descripcion:'Arreglo Auto',
        importe:20000
    },
    {
        fecha:'11-12-1980',
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:8500
    },
    {
        fecha:'11-12-1980',
        categoria:'Varios',
        descripcion:'Ropa Super Cool',
        importe:4500
    },
    {
        fecha:'11-12-1980',
        categoria:'Servicios',
        descripcion:'Gas',
        importe:4000
    },
    {
        fecha:'11-12-1980',
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:2500
    },
    {
        fecha:'11-12-1980',
        categoria:'Varios',
        descripcion:'Celular',
        importe:50000
    },
    {
        fecha:'11-12-1980',
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:2500
    },    
    {
      fecha:'11-12-1980',
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    },
    {
      fecha:'11-12-1980',
      categoria:'Servicios',
      descripcion:'Electricidad',
      importe:3000
    },
    {
      fecha:'11-12-1980',
      categoria:'Alimentos',
      descripcion:'Verduleria',
      importe:5000
    },
    {
      fecha:'11-12-1980',
      categoria:'Movilidad',
      descripcion:'Arreglo Auto',
      importe:20000
    },
    {
      fecha:'11-12-1980',
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:8500
    },
    {
      fecha:'11-12-1980',
      categoria:'Varios',
      descripcion:'Ropa Super Cool',
      importe:4500
    },
    {
      fecha:'11-12-1980',
      categoria:'Servicios',
      descripcion:'Gas',
      importe:4000
    },
    {
      fecha:'11-12-1980',
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    },
    {
      fecha:'11-12-1980',
      categoria:'Varios',
      descripcion:'Celular',
      importe:50000
    },
    {
      fecha:'11-12-1980',
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    }
  ];  */
  lista2Gastos = [
    {
        fecha:new Date("1978-10-2"),
        categoria:'Servicios',
        descripcion:'Electricidad',
        importe:3000
    },
    {
        fecha:new Date("2022-1-4"),
        categoria:'Alimentos',
        descripcion:'Verduleria',
        importe:5000
    },
    {
        fecha:new Date("2000-10-5"),
        categoria:'Movilidad',
        descripcion:'Arreglo Auto',
        importe:20000
    },
    {
        fecha:new Date("1980-4-11"),
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:8500
    },
    {
        fecha:new Date("1980-10-11"),
        categoria:'Varios',
        descripcion:'Ropa Super Cool',
        importe:4500
    },
    {
        fecha:new Date("1980-10-11"),
        categoria:'Servicios',
        descripcion:'Gas',
        importe:4000
    },
    {
        fecha:new Date("1980-10-11"),
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:2500
    },
    {
        fecha:new Date("1980-10-11"),
        categoria:'Varios',
        descripcion:'Celular',
        importe:50000
    },
    {
        fecha:new Date("1980-10-11"),
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:2500
    },    
    {
      fecha:new Date("1980-10-11"),
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Servicios',
      descripcion:'Electricidad',
      importe:3000
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Alimentos',
      descripcion:'Verduleria',
      importe:5000
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Movilidad',
      descripcion:'Arreglo Auto',
      importe:20000
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:8500
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Varios',
      descripcion:'Ropa Super Cool',
      importe:4500
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Servicios',
      descripcion:'Gas',
      importe:4000
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Varios',
      descripcion:'Celular',
      importe:50000
    },
    {
      fecha:new Date("1980-10-11"),
      categoria:'Alimentos',
      descripcion:'Supermercado',
      importe:2500
    }
  ];
  dateKIKI:Date=new Date();

  constructor(private fechaService: FechaService,private gastoService:GastosService) {}
  
  ngOnInit(): void {
    this.fechaActual = this.fechaService.actual();
    this.obtenerGastos();/*
    this.newFecha = new Date();*/
    this.pintarDatos(this.lista2Gastos)    
  }
  

  // Obtener Gastos de la API
  obtenerGastos(){
    this.gastoService.obtenerGastos().subscribe(data =>{
      this.listaGastos=data;
    });
  }
  // Pintar Datos
  pintarDatos(datos:any){
    this.lista = datos;
  }
  // Prueba
  openGasto(){
    this.fechaActual = this.fechaService.actual();
  }
  /*==================================================== */
  /*--------------Modales Metodos CRUD-------------------*/

  /*------------NUEVO GASTO---------------*/  
  guardarGasto(){
    console.log("cococo    "+this.newFecha.valueOf())
    const nuevoGasto = {      
      fecha:this.newFecha.valueOf(),
      categoria:this.newCategoria,
      descripcion:this.newDescripcion,
      importe:this.newImporte
    }
    console.log(nuevoGasto)
    
    console.log("Hola    " + new Date(nuevoGasto.fecha))/*
    this.gastoService.guardarGasto(nuevoGasto).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{
        this.obtenerGastos();
      }
    )*/
  }

  /*--------EDITAR GASTO------------*/

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
      descripcion:this.newDescripcion,
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

  /*------BORRAR GASTO-------------------*/

  //BOTON abrir modal: Capturar Id y GASTO
  trashId(id:any,gasto:Gasto[]): void{
    this.borrarId = id;   
    console.log(this.borrarId);  
  }
  
  //BOTON ELIMINAR GASTO
  eliminarEducacion(): void{
    this.gastoService.borrarGasto(this.borrarId).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerGastos()})
  }

  /*==================================================== */

  // BOTONES DE PAGINACION
  nextPage(){
    this.page = this.page +5;
  }
  previusPage(){
    this.page = this.page -5;
  }

  /*==================================================== */

  // METODOS DE ORDENAMIENTO - (Recibiendo Input)
  recibirOrden(mensaje:string){
    this.orden = mensaje;
    this.page=0;
  }

  juju(){
    console.log(this.lista2Gastos[0].fecha)
    console.log(this.dateKIKI)
  }

}
