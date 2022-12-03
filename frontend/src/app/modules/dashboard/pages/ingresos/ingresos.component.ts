import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ingreso } from '../../model/ingreso';
import { FechaService } from '../../services/fecha.service';
import { IngresosService } from '../../services/ingresos.service';

@Component({
  selector: 'app-ingresos',
  templateUrl: './ingresos.component.html',
  styleUrls: ['./ingresos.component.css']
})
export class IngresosComponent implements OnInit {

  fecha:any;
  active:boolean=true;

  gastoForm:FormGroup;

  listaIngreso:any[]=[];

  nuevoIngreso:Ingreso[]=[];
  newFecha:Date=new Date();
  newCategoria:string="";
  newDescripcion:string="";
  newImporte?:number;
  editId:number=0;
  borrarId:number=0;

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

  quantum = {
    "amount": 1289.444444,
    "date": "2022-12-31",
    "type": "MONTHLY",
    "currencyId": 1
  }


  
  
 
  
  constructor(private fechaService: FechaService,private formBuilder:FormBuilder, private ingresoService:IngresosService) {
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
    this.obtenerIngresos()
  }

  obtenerIngresos(){
    this.ingresoService.obtenerIngresos().subscribe(data =>{
    this.listaIngreso=data.response;
    console.log(this.listaIngreso)
    });
  }

  /*============================================================*/
  
  // METODOS CRUD
  
  /*-------GUARDAR NUEVO INGRESO----------------*/

  guardarIngreso(){    
    const nuevoGasto = this.gastoForm.value;
    console.log(nuevoGasto);
    this.gastoForm.reset();
    console.log(this.quantum)

    this.ingresoService.guardarIngreso(this.quantum).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerIngresos()}
    ) 
  }

  /*-------EDITAR INGRESO-----------------------*/

  editableId(id:number,ingreso:any){
    const editableGasto = ingreso;
    this.editId = id;
    console.log(id);
    console.log(ingreso);
  }
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  actualizarIngreso(){
    const nuevoIngreso = this.gastoForm.value;
    console.log(nuevoIngreso);
    this.gastoForm.reset();
    const editId = this.editId;
    this.ingresoService.actualizarIngreso(editId,nuevoIngreso,this.headers).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerIngresos()}
    )
  }

  /*-------ELIMINAR INGRESO-----------------------*/

  trashId(id:number):void{
    this.borrarId = id;   
    console.log(this.borrarId);
  }
  eliminarIngreso(): void{
    this.ingresoService.borrarIngreso(this.borrarId).subscribe(
      data=>{},
      (error) => {
        alert("Algo ha fallado: " + error);
      },
      ()=>{this.obtenerIngresos()}
    )
  }

  /*============================================================*/

  // BOTONES DE PAGINACION
  nextPage(){
    this.page = this.page +5;
  }
  previusPage(){
    this.page = this.page -5;
  }

  /*============================================================*/

  // METODOS DE ORDENAMIENTO - (Recibiendo Input)
  recibirOrden(mensaje:string){
    this.orden = mensaje;
    this.page=0
  }

  /*============================================================*/

  // VALIDATORS

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

}
