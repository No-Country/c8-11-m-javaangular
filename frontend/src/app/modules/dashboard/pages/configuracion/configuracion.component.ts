import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-configuracion',
  templateUrl: './configuracion.component.html',
  styleUrls: ['./configuracion.component.css']
})
export class ConfiguracionComponent implements OnInit {

  firstName:string="Usuario";
  lastName:string="Registrado";
  email:string="usuario@email.com";
  editar:boolean=true;
  light:boolean=true;


  constructor() { }
  
  ngOnInit(): void {
    const nombre = sessionStorage.getItem("firstName");
    const apellido = sessionStorage.getItem("lastName");
    const email = sessionStorage.getItem("email");
    if (nombre !==null){
      this.firstName=nombre
    }
    if (apellido !== null) {
      this.lastName = apellido
    }
    if (email !== null){
      this.email = email
    }
  }  

  cambioRegistro(){
    console.log("Cambiar Registro")
  }
  restablecer(){
    console.log("Restablecer")
  }

}