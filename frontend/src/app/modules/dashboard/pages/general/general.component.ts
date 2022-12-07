import { Component, OnInit, SimpleChanges } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { Observable, Subscription } from 'rxjs';
import { FechaService } from '../../services/fecha.service';
import { GastosService } from '../../services/gastos.service';
import { GeneralService } from '../../services/general.service';
Chart.register(...registerables);

@Component({
  selector: 'app-general',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.css']
})
export class GeneralComponent implements OnInit {

  nombreUsuario:string="Usuario";
  
  infoGeneral$:Subscription;
  infoGeneral:any;
  hoy:any;
  balanceIngreso:any;
  balanceGasto:any;/*
  balanceGastos$:Subscription;*/

  listaMovimientos = [
    {
      fecha:new Date('2022-12-7'),
      categoria:'Anual',
      descripcion:'Acciones en CHARTA',
      importe:2500000
    },   
    {
        fecha:new Date("2022-12-6"),
        categoria:'Servicios',
        descripcion:'Electricidad',
        importe:3000
    },
    {
        fecha:new Date("2022-12-4"),
        categoria:'Alimentos',
        descripcion:'Verduleria',
        importe:5000
    },
    {
      fecha:new Date('2022-12-3'),
      categoria:'Mensual',
      descripcion:'Sueldo',
      importe:150000
    },
    {
        fecha:new Date("2022-12-3"),
        categoria:'Movilidad',
        descripcion:'Arreglo Auto',
        importe:20000
    },
    {
      fecha:new Date('2022-12-3'),
      categoria:'Diario',
      descripcion:'Acciones en twitter',
      importe:300
    },
    {
        fecha:new Date("2022-12-2"),
        categoria:'Alimentos',
        descripcion:'Supermercado',
        importe:8500
    },
    {
        fecha:new Date("2022-12-2"),
        categoria:'Varios',
        descripcion:'Ropa Super Cool',
        importe:4500
    },
    {
        fecha:new Date("2022-12-1"),
        categoria:'Servicios',
        descripcion:'Gas',
        importe:4000
    },
    {
      fecha:new Date('2022-11-30'),
      categoria:'Semanal',
      descripcion:'Ingreso panadería',
      importe:20000
    }
  ];

  constructor(private generalService:GeneralService, 
              private gastoService: GastosService,
              private fechaservice:FechaService) {
        
      this.infoGeneral$ = this.generalService.obtenerDatos().subscribe(
        (data)=>{this.infoGeneral$=data.response.firstName;
          console.log(this.infoGeneral$)
        }        
      )
  }
      
  

  ngOnInit(): void {
    this.renderChart('myChart');
    const userName = sessionStorage.getItem('UserName');
    if (userName){
      this.nombreUsuario=userName
    }
    this.infoGeneral$ = this.generalService.obtenerDatos().subscribe(
      (data)=>{this.infoGeneral$=data.response.firstName;
        console.log(this.infoGeneral$)
      }        
    )
    console.log(this.balanceIngreso);
    this.obtenerMovimientos();
    /*this.obtenerGastos();*/
    this.hoy = this.fechaservice.fecha();
  }

  ngOnChanges(changes:SimpleChanges){
    this.infoGeneral$ = this.generalService.obtenerDatos().subscribe(
      (data)=>{this.infoGeneral$=data.response.firstName;
        console.log(this.infoGeneral$)
      }        
    )
  }

  obtenerMovimientos(){         
      this.infoGeneral = this.generalService.obtenerDatos().subscribe({
        next:(data) =>{this.infoGeneral=data.response},
        error:(error)=>{console.error(error)}
      });
  }


  obtenerGastos(){
    this.gastoService.obtenerGastos().subscribe({
      next: (data)=>{       
        const listaGastos = JSON.stringify(data.response);
        sessionStorage.setItem("listaGastos",listaGastos);
      },   
      error: (error)=> {
        console.error("Los datos del servidor no llegan");
        console.log(error);      
      },
      complete: ()=>{
        console.log("Complete")
      }
    });    
  }


  renderChart( id:any){
    const myChart = new Chart (id, {
      type: "line",
      data: {
        labels:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre'] ,
        datasets: [{
          label: 'Gastos',
          data:[3000,4000,5000,3000,3000,4000,5000,3000,7000,10000] ,
          borderColor: [
            'rgba(93, 33, 210)'
          ],
          backgroundColor:
          ['rgba(93, 33, 210, 0.2)'],
          tension: 0.2,
          fill:true
        },
        {
          label: 'Ingresos',
          data:[2000,3000,4000,5000,7000,10000,9000,3500,2000,6000],
          borderColor: [
            'rgba(255, 99, 12, 1)'
          ],
          backgroundColor:[
            'rgba(255, 99, 12, 0.2)'
          ],
          tension: 0.2,
          fill:true
        }]
      }
    });
  }

  editableId(){
    console.log("Hola")
  }
  trashId(){
    console.log("Hola")
  }
}
