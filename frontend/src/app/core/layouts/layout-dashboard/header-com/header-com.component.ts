import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SideService } from '../service/side.service';

@Component({
  selector: 'header-layout',
  templateUrl: './header-com.component.html',
  styleUrls: ['./header-com.component.css']
})
export class HeaderComComponent implements OnInit {

  changer: boolean = true;

  constructor(public sideSer: SideService) { }
  

  ngOnInit(): void {
  
  }

  cambiar(){
    this.changer = !this.changer;
    // this.sideSer.$change = !this.sideSer.change;
    this.sideSer.$change.emit(this.changer);
    
  }

}
