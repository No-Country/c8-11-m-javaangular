import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HojaService } from 'src/app/services/hoja.service';
import { Gasto } from '../../model/gasto';
import { ResGastos } from '../../model/res-gastos';
import { FechaService } from '../../services/fecha.service';
import { GastosService } from '../../services/gastos.service';

@Component({
  selector: 'app-gastos',
  templateUrl: './gastos.component.html',
  styleUrls: ['./gastos.component.css']
})
export class GastosComponent implements OnInit {

  fechaActual:any;
  // Vistas Tabla/Tarjeta
  active:boolean=true;

  listaGastos:any;

  // CRUD
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

  // Formularios
  addGastoForm:FormGroup;
  editGastoForm:FormGroup;

  form:FormGroup | undefined;

  lista:Gasto[]=[];
  datos:any;  

  lista2Gastos = [
    {
        fecha:new Date("2022-1-4"),
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
        fecha:new Date("2021-8-6"),
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:8500
    },
    {
        fecha:new Date("1999-2-3"),
        categoria:'Varios',
        descripcion:'Ropa Super Cool',
        importe:4500
    },
    {
        fecha:new Date("2013-8-21"),
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
    },
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

  recargar:number=0;

  constructor(private fechaService: FechaService,
              private gastoService:GastosService,
              private formBuilder:FormBuilder, 
              private router:Router) {
    this.addGastoForm = this.formBuilder.group(
      {      
        fecha: ['', [Validators.required]],
        categoriaId: ['',[Validators.required]],
        importe:['',[Validators.required,Validators.min(0)]],
        descripcion:['',[Validators.required,Validators.maxLength(20)]],
        monedaId:1,
        esIncluida:true
      }
    )
    this.editGastoForm = this.formBuilder.group(
      {      
        fecha: ['', [Validators.required]],
        categoriaId: ['',[Validators.required]],
        importe:['',[Validators.required,Validators.min(0)]],
        descripcion:['',[Validators.required,Validators.maxLength(20)]],
        monedaId:1,
        esIncluida:true
      }
    )
  }
  
  ngOnInit(): void {
    this.obtenerDatos();
    this.lista = this.lista2Gastos;
         
  }

  recargate(){
    this.recargar=this.recargar+1;
  }


  // Obtener Gastos
  obtenerDatos(){
    this.gastoService.obtenerGastos().subscribe(
      (data) =>{
        this.listaGastos = data.response;      
        console.log(this.listaGastos);
        setTimeout(this.recargate,2000)
      },
      (error) => {
        console.error("Los datos del servidor no llegan");
        console.log(error);
      },
      ()=>{
        console.log("Datos cargados");
        console.log(this.listaGastos);
        this.recargar=this.recargar+1;  
    })    
  }
  
  /*==================================================== */
  /*--------------Modales Metodos CRUD-------------------*/

  /*------------NUEVO GASTO---------------*/  
  guardarGasto(){
    // Almacenando el Formulario
    const nuevoGasto = this.addGastoForm.value;
    console.log("NUEVO  GASTO:");
    console.log(nuevoGasto);
    // Reseteando el Formulario
    this.addGastoForm = this.formBuilder.group(
      {      
        fecha: [''],
        categoriaId: [''],
        importe:[''],
        descripcion:[''],
        monedaId:1,
        esIncluida:true
      }
    )
    // Servicio Gasto Service  
    this.gastoService.guardarGasto(nuevoGasto).subscribe(
      (data)=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{
        this.obtenerDatos();
        console.log("Gasto creado")
      }
    )
  }

  /*--------EDITAR GASTO------------*/

  //Boton abrir modal: Capturar Id y experiencia
  editableId(id:any,expense: any){
    const editableGasto = expense;
    this.editId = id;
    console.log(id);
    console.log(expense);
    const categoria = editableGasto.categoria;
    console.log(categoria);
    let categoriaId = 0;
    switch(categoria){
      case "Alimentos":categoriaId=1
        break;
      case "Servicios":categoriaId=2
        break;
      case "Movilidad":categoriaId=3
        break;
      case "Varios":categoriaId=4
        break;
      default:
        break;
    }
    
    
    /* Mostrar datos en el modal *//*
    this.newFecha = editableGasto.fecha;
    this.newCategoria = editableGasto.categoriaId;
    this.newDescripcion = editableGasto.descripcion;
    this.newImporte = editableGasto.importe;*/

    this.editGastoForm = this.formBuilder.group(
      {      
        fecha: [editableGasto.fecha],
        categoriaId: [categoriaId],
        importe:[editableGasto.importe],
        descripcion:[editableGasto.descripcion],
        monedaId:1,
        esIncluida:true
      }
    )
    console.log(this.editGastoForm.value)
  }

  // Boton Actualizar Experiencia

  headers = 'Access-Control-Allow-Origin';

  actualizarGasto(): void{
    const nuevoGasto = this.editGastoForm.value;
    console.log(nuevoGasto);
    this.editGastoForm.reset();
    const editId = this.editId;

    const quantum = { "amount": 123.5,
    "description": "Hola",
    "categoryId": 3,
    "currencyId": 2,
    "date": "2022-12-30",
    "isIncluded": true}
    console.log(quantum);


    this.gastoService.actualizarGasto(editId,quantum,this.headers).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerDatos()})
  }

  /*------BORRAR GASTO-------------------*/

  //BOTON abrir modal: Capturar Id y GASTO
  trashId(id:any): void{
    this.borrarId = id;   
    console.log(this.borrarId);  
  }
  
  //BOTON ELIMINAR GASTO
  eliminarGasto(): void{
    this.gastoService.borrarGasto(this.borrarId).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerDatos()}
    )
  }

  /*==================================================== */

  // BOTONES DE PAGINACION
  nextPage(){
    this.page = this.page +10;
  }
  previusPage(){
    this.page = this.page -10;
  }

  /*==================================================== */

  // METODOS DE ORDENAMIENTO - (Recibiendo Input)
  recibirOrden(mensaje:string){
    this.orden = mensaje;
    this.page=0;
  }

  /*==================================================== */

  // VALIDATORS
  
  // Propiedades para los validadores
  get Fecha() { 
    return this.addGastoForm.get('fecha'); 
  }
  get Categoria() {
    return this.addGastoForm.get('categoria')
  }
  get Importe() { 
    return this.addGastoForm.get('importe'); 
  }
  get Descripcion() {
    return this.addGastoForm.get('descripcion')
  }
  clearValidatorsAdd() {
    this.addGastoForm.reset(this.addGastoForm.value);
  }
  clearValidatorsEdit() {
    this.editGastoForm.reset(this.editGastoForm.value);
  }

  filtrar(){
    console.log("se esta filtrando")
  }
}
