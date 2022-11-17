import {  Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SwitchService } from '../../../services/switch.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'dashboard-layout',
  templateUrl: './menu-dashboard.component.html',
  styleUrls: ['./menu-dashboard.component.scss'],
})
export class MenuDashboardComponent implements OnInit {

  modalSwith: boolean = false;

  constructor(public router: Router, private modalService: SwitchService) { }

  ngOnInit(): void {
    this.modalService.$modal.subscribe((valor) => {
      this.modalSwith = valor
    })
  }


  openModal(): void {
    this.modalSwith = true;
  }

  closeSession() {
    Swal.fire({
      title: '¿Estas seguro?',
      text: "Seras redirigido a la landing page",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, estoy seguro!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.router.navigateByUrl('/landing')
      }
    })
  }
}
