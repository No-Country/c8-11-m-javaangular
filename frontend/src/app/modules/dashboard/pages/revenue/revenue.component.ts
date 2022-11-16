import { ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'revenue-component',
  templateUrl: './revenue.component.html',
  styleUrls: ['./revenue.component.scss'],
  encapsulation: ViewEncapsulation.Emulated,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RevenueComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
