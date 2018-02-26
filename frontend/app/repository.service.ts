import { Injectable } from '@angular/core';
import { CustomerPriceDetails } from './Models/customer-price-details';
//import { PFJTotal,BetterOf,RetailMinus,TotalRetail,Funded,CCC } from './Models/pfj-tiles';


@Injectable()
export class RepositoryService {
  customerPricingDetails:CustomerPriceDetails[] = [];
  selctedCustomerPricingDetails:CustomerPriceDetails = new CustomerPriceDetails();
  selectedDataPeriod:string;
  constructor() { }

}
