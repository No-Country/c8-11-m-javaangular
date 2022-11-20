import { Component, OnInit } from '@angular/core';

import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;  
  ocultar: boolean = true; 

  constructor(private formBuilder:FormBuilder, private router: Router) {
    this.loginForm = this.formBuilder.group(
      {      
        email: ['', [Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(6),Validators.maxLength(22)]]
      }
  )}

  ngOnInit(): void {
  }

  onSubmit(event:any) {
    // TODO: Use EventEmitter with form value
    event.preventDefault();
    console.log(this.loginForm.value);  
    const a = this.loginForm.value;
    console.log(a);
    localStorage.setItem("usuario",a);
    if (a.email == "usuario@email.com" && a.password == "123456"){
      this.router.navigate(['dashboard']);
    } else {
      this.usuarioIncorrecto();
    }
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
