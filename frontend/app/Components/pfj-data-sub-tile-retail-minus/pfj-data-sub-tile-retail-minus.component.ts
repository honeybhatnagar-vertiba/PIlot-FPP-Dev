import { Component, OnInit, Input } from '@angular/core';
import { RetailMinus } from '../../Models/pfj-tiles';

@Component({
  selector: 'app-pfj-data-sub-tile-retail-minus',
  templateUrl: './pfj-data-sub-tile-retail-minus.component.html',
  styleUrls: ['./pfj-data-sub-tile-retail-minus.component.css']
})
export class PfjDataSubTileRetailMinusComponent implements OnInit {
  @Input() retailMinus : RetailMinus;
  constructor() { }

  ngOnInit() {
  }

}
