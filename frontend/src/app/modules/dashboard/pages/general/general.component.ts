import { ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'general-component',
  templateUrl: './general.component.html',
  styleUrls: ['./general.component.scss'],
  encapsulation: ViewEncapsulation.Emulated,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GeneralComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
