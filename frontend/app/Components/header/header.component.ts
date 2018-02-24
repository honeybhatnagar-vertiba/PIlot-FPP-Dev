import { Component, OnInit,ElementRef } from '@angular/core';
declare var $:any;
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor() { 
  }

  ngOnInit() {
    $(document).ready(function() {
      // get current URL path and assign 'active' class
      var pathname = window.location.pathname;
      console.log("pathname "+pathname);
      $('.nav > li > a[href="'+pathname+'"]').parent().addClass('active');
    });
  }

}
