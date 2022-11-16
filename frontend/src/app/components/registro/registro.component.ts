import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  registroForm:FormGroup; 
  ocultar:boolean=true;   

  constructor(private formBuilder:FormBuilder) {
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

  onSubmit() {
    // TODO: Use EventEmitter with form value
    console.log(this.registroForm.value);
  }

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

