import { Component } from '@angular/core';
import { UserService } from "app/services/user.service";

declare var tableau: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{

  users;
  tableauViz;

  constructor(private userService: UserService) {
	this.users = userService.getUsers();
	var that = this;
	setTimeout(function(){
		that.loadtableau('https://public.tableau.com/views/USMap_21/USMap?:embed=y');
	}, 3000); 
  }
  
  loadtableau(tableauURL){

	if(this.tableauViz != undefined){
		this.tableauViz.dispose();
	}	
	
	var placeholderDiv = document.getElementById('tableauViz');
	var options = {
	  hideTabs: true,
	  width: '900px',
	  height: '500px',
	  onFirstInteractive: function() {
	  }
	};
	
	this.tableauViz = new tableau.Viz(placeholderDiv, tableauURL, options);
	
	(<HTMLInputElement> document.getElementById("ytd_mtd_toggle")).checked = false;

  }
  
  toggleYTDandMTD(){
	  
	  if(this.tableauViz != undefined){
		  
		  var activeSheet = this.tableauViz.getWorkbook().getActiveSheet();
		  if((<HTMLInputElement> document.getElementById("ytd_mtd_toggle")).checked){
			  
			  activeSheet.applyFilterAsync(
				"State",
				"Tennessee",
				tableau.FilterUpdateType.REPLACE);  
		  }
		  else{
			  activeSheet.clearFilterAsync("State");
		  }	
	  }
	  
  }
}
