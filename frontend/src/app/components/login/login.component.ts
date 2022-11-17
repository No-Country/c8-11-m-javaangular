import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
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
    if (a.email == "usuario@mail.com" && a.password == "123456"){
      this.router.navigate(['dashboard']);
    } else {
      this.router.navigate(['/login']);
    }
  }
  get Email() {
    return this.loginForm.get('email');
  }
  get Password() {
    return this.loginForm.get('password')
  }

}
