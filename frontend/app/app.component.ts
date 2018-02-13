import { Component } from '@angular/core';
import { UserService } from "app/services/user.service";

declare var tableau: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'app works!';

  users;
  tableauViz;
  placeholderDiv;
  url;
  options;
  
  constructor(private userService: UserService) {
	this.users = userService.getUsers();
	var that = this;
	setTimeout(function(){
		that.loadtableau('https://public.tableau.com/views/USregionalheatmap/Sheet2?:embed=y');
	}, 3000); 
  }
  
  loadtableau(tableauURL){
	if(tableauURL != this.url){
		
		if(this.tableauViz != undefined){
			this.tableauViz.dispose();
		}	
		
		
		this.placeholderDiv = document.getElementById('tableauViz');
		this.url = tableauURL;
		this.options = {
		  hideTabs: true,
		  width: '900px',
		  height: '500px',
		  onFirstInteractive: function() {
			// The viz is now ready and can be safely used.
		  }
		};
		this.tableauViz = new tableau.Viz(this.placeholderDiv, this.url, this.options);
	}
	
  }
}
