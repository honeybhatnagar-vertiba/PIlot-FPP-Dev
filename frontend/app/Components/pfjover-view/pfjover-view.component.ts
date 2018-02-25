import { Component, OnInit } from '@angular/core';
import { ServiceConsumer } from '../../service-consumer.service';
import { RepositoryService } from '../../repository.service';
import { PfjOverviewOperations } from '../../Models/pfjoverview-operations';
@Component({
  selector: 'app-pfjover-view',
  templateUrl: './pfjover-view.component.html',
  styleUrls: ['./pfjover-view.component.css']
})
export class PfjoverViewComponent implements OnInit {

  constructor(private serviceConsumer: ServiceConsumer ,private repository : RepositoryService ) { }

  ngOnInit() {
    if(this.repository.customerPriceDetails == null)
    {
    //Get customer pricing details from service.
    this.serviceConsumer.GetCustomerPricingDetails()
    .subscribe(data=>this.PopulateCustomerPricingDetails(data),error=>console.log(error));
    }
    else
    {
      console.log("I am present in repository");
    }
  }

  PopulateCustomerPricingDetails(data)
  {
    new PfjOverviewOperations().PopulateCustomerPricingDetails(data);
  }

}
