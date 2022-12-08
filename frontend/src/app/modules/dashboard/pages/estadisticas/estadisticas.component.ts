import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { GastosService } from '../../services/gastos.service';
import { IngresosService } from '../../services/ingresos.service';
import { Gasto } from '../../model/gasto';
import { Ingreso } from '../../model/ingreso';

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css']
})
export class EstadisticasComponent implements OnInit {
   
  
  listaIngreso:Ingreso[]=[];
  listaGasto:Gasto[]=[];

  gastoTotal:number=0;
  ingresoTotal:number=0;

  listaIngresoImporte:any[]=[];
  listaGastoImporte:any[]=[];

  labelCategoria:any[]=[];  

  constructor(
    private gasto: GastosService,
    private ingreso: IngresosService
  ) { }

  ngOnInit(): void {
    
  this.gasto.obtenerGastos().subscribe(
    data =>{
     this.listaGasto = data; 
     if(this.listaGasto!=null){
        this.gastoTotal = this.listaGasto.reduce((sum:number, total:Gasto) => sum + total.importe,0)
      console.log(this.gastoTotal)
     }     
    console.log(this.listaGasto)
  })
    
    console.log(this.listaGasto)
    /*
    this.obtenerIngresos();

    this.obtenerGastoTotal();
    this.obtenerIngresoTotal();

    this.obtenerLabelsRadar();*/

    /*RENDER DE LAS ESTADISTICAS*/


    this.renderPieChart(this.gastoTotal,this.ingresoTotal,'pieChartGeneral');
    this.renderRadarChart(this.listaGastoImporte ,this.labelCategoria,'radarChartIncomesExpenses');
    this.renderLineChart(this.listaGastoImporte, this.listaIngresoImporte, 'barChartIncomesExpensesMonths')
  }
  
/* GET DATOS */
  /*
  obtenerIngresos(){
    this.ingreso.obtenerIngresos().subscribe(
      data =>{
        this.listaIngreso = data;  
        console.log(this.listaIngreso)})
  }

  /*FILTRADO DE DATA PARA LAS ESTADISTICAS*/
  
/** 
obtenerGastoTotal(){
 this.gastoTotal = this.listaGasto.reduce((sum:number, total:Gasto) => sum + total.importe,0);
 console.log(this.gastoTotal);
}

obtenerIngresoTotal(){
  this.ingresoTotal = this.listaIngreso.reduce((sum:number, total:Ingreso) => sum + total.importe, 0);
}

obtenerLabelsRadar(){
  this.labelCategoria = [...new Set(this.listaGasto.map(g => g.categoria))] 
}
*/
  /* RENDER CHARTS*/ 
  renderPieChart(gastoTotal:number, ingresoTotal:number ,id:any){
    const chart  = new Chart(id,{
      type:"pie",
      data:{
        labels:['Gasto total', 'Ingreso total'],
        datasets:[
          {
            data:[gastoTotal, ingresoTotal],
            backgroundColor:[
              'rgba(93, 33, 210, 0.2)',
              'rgba(255, 99, 12, 0.2)'
            ],
            borderColor:[
              'rgba(93, 33, 210)',
              'rgba(255, 99, 12)'
            ]
          }
        ]
      } 
    });
  }

  renderRadarChart( listaGastoImporte:any,labelCategoria:any ,id:any){
    const chart  = new Chart(id,{
      type:"radar",
      data:{
        labels: labelCategoria
        /*
        comida;
        viajes;
        servicios;
        etc
        */ ,
        datasets: [{
          label: 'Enero',
          data: listaGastoImporte,
          fill: true,
          backgroundColor: 'rgba(255, 99, 132, 0.2)',
          borderColor: 'rgb(255, 99, 132)',
          pointBackgroundColor: 'rgb(255, 99, 132)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgb(255, 99, 132)'
        }                    
      ]}
    });
  }

  renderLineChart(listaGastoImporte:any,listaIngresoImporte:any ,id:any){
    const chart  = new Chart(id,{
      type:"bar",
      data:{
        labels:['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio'],
        datasets:[
          {
            label:'Ingresos',
            data: listaIngresoImporte   /*[2000,3000,4000,5000,7000,10000,9000,3500,2000,6000]*/,
            backgroundColor:[
              'rgba(93, 33, 210, 0.5)'    
            ],
            borderColor:
              'rgba(93, 33, 210)'
          },
          {
          label:'Gastos',
          data: listaGastoImporte /*[ 3000,4000,5000,3000,3000,4000,5000,3000,7000,10000]*/,
          backgroundColor:[
          'rgba(255, 99, 12, 0.5)'    
          ],
          borderColor:
          'rgba(255, 99, 12, 1)'
          }
        ]
      } 
    });
  }
}
