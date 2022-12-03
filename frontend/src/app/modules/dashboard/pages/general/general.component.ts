import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
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

  constructor(private generalService:GeneralService, private gastoService: GastosService) { }

  ngOnInit(): void {
    this.renderChart('myChart');
    const nombreishon = sessionStorage.getItem('UserName');
    if (nombreishon){
      this.nombreUsuario=nombreishon
    }
    this.obtenerMovimientos();
    this.obtenerGastos();
  }

  obtenerMovimientos(){
    this.generalService.obtenerDatos().subscribe(data =>{
      console.log(data)
    });
  }
  obtenerGastos(){
    this.gastoService.obtenerGastos().subscribe(
      data =>{
        const listaGastos = JSON.stringify(data.response);
        sessionStorage.setItem("listaGastos",listaGastos);        
      },    
      err => {
        console.error("Los datos del servidor no llegan");
        console.log(err);
      }
    );
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
}
