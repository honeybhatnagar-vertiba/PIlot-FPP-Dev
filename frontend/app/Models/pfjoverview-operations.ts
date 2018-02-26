import { CustomerPriceDetails } from '../Models/customer-price-details';

export class PfjOverviewOperations {
    public PopulateCustomerPricingDetails(data): CustomerPriceDetails[]
    {
        let arrCustomerPricingDetails : CustomerPriceDetails[]=[];
        let arrCustPricingDetailsService:any[] = data;
        arrCustPricingDetailsService.forEach(c => {
            let customerPriceDetails:CustomerPriceDetails = new CustomerPriceDetails();
            customerPriceDetails.temporalPeriod = c.temporalPeriod;
            customerPriceDetails.lastClosedPeriod = c.lastClosedPeriod;
            customerPriceDetails.pfjTotal = c.pFJTotal;
            customerPriceDetails.betterOf = c.betterOf;
            customerPriceDetails.totalRetail = c.totalRetail;
            customerPriceDetails.retailMinus = c.retailMinus;
            customerPriceDetails.funded = c.funded;
            customerPriceDetails.ccc = c.ccc;
            arrCustomerPricingDetails.push(customerPriceDetails);
        });
        /*let c = data;
        let customerPriceDetails:CustomerPriceDetails = new CustomerPriceDetails();
        customerPriceDetails.temporalPeriod = c.temporalPeriod;
        customerPriceDetails.pfjTotal = c.pfjTotal;
        customerPriceDetails.betterOf = c.betterOf;
        customerPriceDetails.totalRetail = c.totalRetail;
        customerPriceDetails.retailMinus = c.retailMinus;
        customerPriceDetails.funded = c.funded;
        customerPriceDetails.ccc = c.ccc;
        arrCustomerPricingDetails.push(customerPriceDetails);
        console.log(JSON.stringify(arrCustomerPricingDetails));*/
        return arrCustomerPricingDetails;
    }
}
