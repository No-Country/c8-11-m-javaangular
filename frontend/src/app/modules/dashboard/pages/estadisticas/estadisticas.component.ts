import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-estadisticas',
  templateUrl: './estadisticas.component.html',
  styleUrls: ['./estadisticas.component.css']
})
export class EstadisticasComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.renderPieChart('pieChartGeneral');
    this.renderRadarChart('radarChartIncomesExpenses');
    this.renderLineChart('barChartIncomesExpensesMonths')
  }


  renderPieChart(id:any){
    const chart  = new Chart(id,{
      type:"pie",
      data:{
        labels:['Gasto total', 'Ingreso total'],
        datasets:[
          {
            data:[10000,10000],
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

  renderRadarChart(id:any){
    const chart  = new Chart(id,{
      type:"radar",
      data:{
        labels: [
          'Comida',
          'Transporte',
          'Servicios',
          'Turismo',
          'Salud',
          'Hobbies',
          'Otros'
        ],
        datasets: [{
          label: 'Enero',
          data: [6500, 5900, 9000, 8100, 560, 5500, 400],
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

  renderLineChart(id:any){
    const chart  = new Chart(id,{
      type:"bar",
      data:{
        labels:['Enero', 'Febrero','Marzo','Abril','Mayo','Junio','Julio'],
        datasets:[
          {
            label:'Ingresos',
            data:[2000,3000,4000,5000,7000,10000,9000,3500,2000,6000],
            backgroundColor:[
              'rgba(93, 33, 210, 0.2)'    
            ],
            borderColor:
              'rgba(93, 33, 210)'
          },
          {
          label:'Gastos',
          data:[3000,4000,5000,3000,3000,4000,5000,3000,7000,10000],
          backgroundColor:[
          'rgba(255, 99, 12, 0.2)'    
          ],
          borderColor:
          'rgba(255, 99, 12, 1)'
          }
        ]
      } 
    });
  }
}
