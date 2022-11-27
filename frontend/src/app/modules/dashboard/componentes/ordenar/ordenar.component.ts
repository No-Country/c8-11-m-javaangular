import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-ordenar',
  templateUrl: './ordenar.component.html',
  styleUrls: ['./ordenar.component.css']
})
export class OrdenarComponent implements OnInit {

  @Output() evento = new EventEmitter<Event>();

  constructor() { }

  ngOnInit(): void {
  }

}
