import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NuevoUsuario } from '../../models/nuevo-usuario';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  registroForm:FormGroup; 
  ocultar:boolean=true;
  
  nuevoUsuario: NuevoUsuario={nombre:"",apellido:"",email:"",password:""};

  constructor(
    private formBuilder:FormBuilder,
    private authService: AuthService) {

    this.registroForm = this.formBuilder.group(
      {      
        nombre: ['', [Validators.required]],
        apellido: ['', [Validators.required]],
        email: ['', [Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(6),Validators.maxLength(22)]]
      }
  )}

  ngOnInit(): void {
  }

  onRegister() {
    // TODO: Use EventEmitter with form value
    this.nuevoUsuario=this.registroForm.value;
    console.log(this.nuevoUsuario);
    this.authService.nuevo(this.nuevoUsuario).subscribe(      
      data => {
        console.log(data)/*
        this.isLogged = true;       
        this.tokenService.setToken(data.token);
        this.router.navigate(['/dashboard']);*/
      },
      err => {/*
        this.isLogged = false;
        this.errMsj = err.error.message;
        alert("Algo ha fallado");
        this.router.navigate(['/']);*/
        console.error("JO JO JO")
        console.log(err);
      }
    )}
  

  /*=================================================*/

  // VALIDATORS  
  get Nombre() { 
    return this.registroForm.get('nombre'); 
  }
  get Apellido() { 
    return this.registroForm.get('apellido'); 
  }
  get Email() { 
    return this.registroForm.get('email'); 
  }
  get Password() {
    return this.registroForm.get('password')
  }

}


