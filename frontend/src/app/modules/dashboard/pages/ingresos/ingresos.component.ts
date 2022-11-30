import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ingreso } from '../../model/ingreso';
import { FechaService } from '../../services/fecha.service';

@Component({
  selector: 'app-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.css']
})
export class IngresosComponent implements OnInit {

  fecha:any;
  active:boolean=true;

  gastoForm:FormGroup;

  nuevoIngreso:Ingreso[]=[];
  newFecha:Date=new Date();
  newCategoria:string="";
  newDescripcion:string="";
  newImporte?:number;

  // Paginación
  page:number=0;
  orden:string="";

  lista2Ingresos = [
    {
        fecha:new Date('2022-3-17'),
        categoria:'Anual',
        descripcion:'Acciones en CHARTA',
        importe:2500000
    },
    {
        fecha:new Date('2021-8-5'),
        categoria:'Anual',
        descripcion:'Acciones en twitter',
        importe:300
    },
    {
        fecha:new Date('2020-10-9'),
        categoria:'Mensual',
        descripcion:'Sueldo',
        importe:150000
    },
    {
        fecha:new Date('2019-5-8'),
        categoria:'Semanal',
        descripcion:'Ingreso panadería',
        importe:20000
    },
    {
        fecha:new Date('1999-6-5'),
        categoria:'Diario',
        descripcion:'Dinero encontrado en la calle',
        importe:8500
    },
    {
        fecha:new Date('2005-4-12'),
        categoria:'Semanal',
        descripcion:'ingreso peluquería',
        importe:10500
    },
    {
        fecha:new Date('1982-4-27'),
        categoria:'Mensual',
        descripcion:'Sueldo 2',
        importe:115000
    },
    {
        fecha:new Date('1995-9-23'),
        categoria:'Diario',
        descripcion:'Regalo de la tia',
        importe:25000
    },
    {
        fecha:new Date('1980-11-12'),
        categoria:'Anual',
        descripcion:'Acciones en Spice Girls',
        importe:50000
    },
    {
      fecha:new Date('2022-3-17'),
      categoria:'Anual',
      descripcion:'Acciones en CHARTA',
      importe:2500000
  },
  {
      fecha:new Date('2021-8-5'),
      categoria:'Anual',
      descripcion:'Acciones en twitter',
      importe:300
  },
  {
      fecha:new Date('2020-10-9'),
      categoria:'Mensual',
      descripcion:'Sueldo',
      importe:150000
  },
  {
      fecha:new Date('2019-5-8'),
      categoria:'Semanal',
      descripcion:'Ingreso panadería',
      importe:20000
  },
  {
      fecha:new Date('1999-6-5'),
      categoria:'Diario',
      descripcion:'Dinero encontrado en la calle',
      importe:8500
  },
  {
      fecha:new Date('2005-4-12'),
      categoria:'Semanal',
      descripcion:'ingreso peluquería',
      importe:10500
  },
  {
      fecha:new Date('1982-4-27'),
      categoria:'Mensual',
      descripcion:'Sueldo 2',
      importe:115000
  },
  {
      fecha:new Date('1995-9-23'),
      categoria:'Diario',
      descripcion:'Regalo de la tia',
      importe:25000
  },
  {
      fecha:new Date('2022-11-24'),
      categoria:'Anual',
      descripcion:'Acciones en Spice Girls',
      importe:50000
  }
  ];
  constante:Date=new Date();
 
  
  constructor(private fechaService: FechaService,private formBuilder:FormBuilder) {
    this.gastoForm = this.formBuilder.group(
      {      
        fecha: ['', [Validators.required]],
        categoria: ['',[Validators.required]],
        importe:['',[Validators.required,Validators.min(0)]],
        descripcion:['',[Validators.required,Validators.maxLength(20)]]
      }
    )
  }

  ngOnInit(): void {
    this.fecha = this.fechaService.actual();
  }

  // Propiedades para los validadores
  get Fecha() { 
    return this.gastoForm.get('fecha'); 
  }
  get Categoria() {
    return this.gastoForm.get('categoria')
  }
  get Importe() { 
    return this.gastoForm.get('importe'); 
  }
  get Descripcion() {
    return this.gastoForm.get('descripcion')
  }

  clearValidators() {
    this.gastoForm.reset(this.gastoForm.value);
  }

  
  // METODOS CRUD
  
  /*-------GUARDAR NUEVO GASTO-------*/





  guardarIngreso(){    
    const nuevoIngreso = {      
      fecha:this.newFecha,
      categoria:this.newCategoria,
      descripcion:this.newDescripcion,
      importe:this.newImporte
    }
    console.log(nuevoIngreso);
    console.log(nuevoIngreso.fecha)
    console.log(typeof nuevoIngreso.fecha);/*
    console.log(new Date(this.fechaService.actual()));*/
    this.newDescripcion="";
    this.newImporte=0;
    this.newCategoria="";/**/
  }

  // BOTONES DE PAGINACION
  nextPage(){
    this.page = this.page +5;
  }
  previusPage(){
    this.page = this.page -5;
  }

  // METODOS DE ORDENAMIENTO - (Recibiendo Input)
  recibirOrden(mensaje:string){
    this.orden = mensaje;
    this.page=0
  }

  // compararFechas

  compararFechas(){
    const fecha1 = this.lista2Ingresos[0].fecha;
    const fecha2 = this.lista2Ingresos[1].fecha;
    console.log(fecha1);
    console.log(fecha2);
    console.log(fecha1<fecha2)
  }

}
