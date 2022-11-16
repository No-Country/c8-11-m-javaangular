import { Component, OnInit } from '@angular/core';

import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;   
  ocultar: boolean = true; 

  constructor(private formBuilder:FormBuilder) {
    this.loginForm = this.formBuilder.group(
      {      
        email: ['', [Validators.required, Validators.email]],
        password: ['',[Validators.required, Validators.minLength(6),Validators.maxLength(22)]]
      }
  )}

  ngOnInit(): void {
  }

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.log(this.loginForm.value);
  }

  get Email() { 
    return this.loginForm.get('email'); 
  }
  get Password() {
    return this.loginForm.get('password')
  }

}
