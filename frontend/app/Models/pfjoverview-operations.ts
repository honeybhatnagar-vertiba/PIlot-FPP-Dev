import { CustomerPriceDetails } from '../Models/customer-price-details';

export class PfjOverviewOperations {
    public PopulateCustomerPricingDetails(data)
    {
        let arrcustomerPricingDetails : CustomerPriceDetails[]=[];
        let arrCustPricingDetailsService:any[] = data;
        arrCustPricingDetailsService.forEach(c => {
            console.log("c "+c);
            let customerPriceDetails:CustomerPriceDetails = new CustomerPriceDetails();
            customerPriceDetails.temporalPeriod = c.temporalPeriod;
            customerPriceDetails.pfjTotal = c.pFJTotal;
            customerPriceDetails.betterOf = c.betterOf;
            customerPriceDetails.totalRetail = c.totalRetail;
            customerPriceDetails.retailMinus = c.retailMinus;
            customerPriceDetails.funded = c.funded;
            customerPriceDetails.ccc = c.ccc;
            arrcustomerPricingDetails.push(customerPriceDetails);
        });
        console.log("Final array object "+ JSON.stringify(arrcustomerPricingDetails));
    }
}
