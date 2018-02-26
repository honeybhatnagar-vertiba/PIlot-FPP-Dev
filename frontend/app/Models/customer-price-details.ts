import { ICustomerPriceDetails } from '../Interface/pfj-interfaces';
import { PFJTotal,BetterOf,TotalRetail,RetailMinus,Funded,CCC } from '../Models/pfj-tiles';
export class CustomerPriceDetails implements ICustomerPriceDetails {
    temporalPeriod: string;
    lastClosedPeriod:string;
    pfjTotal: PFJTotal;
    betterOf: BetterOf;
    totalRetail: TotalRetail;
    retailMinus: RetailMinus;
    funded: Funded;
    ccc: CCC;
}
