import { Component, OnInit } from '@angular/core';
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
        fecha:'1980-11-12',
        categoria:'Anual',
        descripcion:'Acciones en CHARTA',
        importe:2500000
    },
    {
        fecha:'2022-11-28',
        categoria:'Anual',
        descripcion:'Acciones en twitter',
        importe:300
    },
    {
        fecha:'1980-11-12',
        categoria:'Mensual',
        descripcion:'Sueldo',
        importe:150000
    },
    {
        fecha:'1980-2-15',
        categoria:'Semanal',
        descripcion:'Ingreso panadería',
        importe:20000
    },
    {
        fecha:'1980-11-12',
        categoria:'Diario',
        descripcion:'Dinero encontrado en la calle',
        importe:8500
    },
    {
        fecha:'1980-11-12',
        categoria:'Semanal',
        descripcion:'ingreso peluquería',
        importe:10500
    },
    {
        fecha:'1980-11-12',
        categoria:'Mensual',
        descripcion:'Sueldo 2',
        importe:115000
    },
    {
        fecha:'1980-11-12',
        categoria:'Diario',
        descripcion:'Regalo de la tia',
        importe:25000
    },
    {
        fecha:'1980-11-12',
        categoria:'Anual',
        descripcion:'Acciones en Spice Girls',
        importe:50000
    }
  ];
 
  
  constructor(private fechaService: FechaService) { }

  ngOnInit(): void {
    this.fecha = this.fechaService.actual()
  }
  guardarIngreso(){    
    const nuevoIngreso = {      
      fecha:this.newFecha,
      categoria:this.newCategoria,
      descripcion:this.newDescripcion,
      importe:this.newImporte
    }
    console.log(nuevoIngreso);
    console.log(new Date(this.fechaService.actual()));
  }

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