import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


import Swal from 'sweetalert2';
import { LoginUsuario } from '../../models/login-usuario';
import { AuthService } from '../../services/auth.service';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;  
  ocultar: boolean = true; 
  isLogged:boolean=false;
  loginUsuario: LoginUsuario={email:"",password:""};
  emailUsuario: string="";
  password: string="";
  errMsj:string="";

  constructor(private formBuilder:FormBuilder, private router: Router,private authService:AuthService,private tokenService:TokenService) {
    this.loginForm = this.formBuilder.group(
      {      
        email: ['', [Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(6),Validators.maxLength(22)]]
      }
  )}

  ngOnInit(): void {
  }

  onLogin(event:any) {
    // TODO: Use EventEmitter with form value
    /*event.preventDefault();
    console.log(this.loginForm.value);  
    const a = this.loginForm.value;
    console.log(a);
    localStorage.setItem("usuario",a);
    if (a.email == "usuario@email.com" && a.password == "123456"){
      this.router.navigate(['dashboard']);
    } else {
      this.usuarioIncorrecto();
    }*/
    this.loginUsuario = this.loginForm.value;
    if (this.loginUsuario.email=="usuario@email.com" && this.loginUsuario.password=="123456"){
      this.router.navigate(['/dashboard'])
    } else {
    console.log("El usuario tiene estos datos:");
    console.log(this.loginUsuario);
    console.log("Se llama al servicio");
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    this.authService.login(this.loginUsuario,headers).subscribe(      
      data => {
        console.log(data)
        this.isLogged = true;     
        this.tokenService.setToken(data.response.jwt);
        this.tokenService.setUserName(data.response.firstName);
        this.router.navigate(['/dashboard']);
      },
      err => {/*
        this.isLogged = false;
        this.errMsj = err.error.message;
        alert("Algo ha fallado");
        this.router.navigate(['/']);*/
        console.error("JO JO JO")
        this.usuarioIncorrecto();
      }
    );}
  }   
  
  
  get Email() { 
    return this.loginForm.get('email'); 
  }
  get Password() {
    return this.loginForm.get('password')
  }

  usuarioIncorrecto() {
    Swal.fire({
      title: 'Usuario NO registrado',
      text: "Algunos de los datos ingresados es incorrecto, o bien, no está registrado",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Quiero registrarme'
    }).then((result:any) => {
      if (result.isConfirmed) {
        this.router.navigateByUrl('/auth/registro')
      }
    })
  }

}
