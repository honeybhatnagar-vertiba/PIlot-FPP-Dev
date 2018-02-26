import { GrossProfitDollars,Margin,Volume,MixPercentage } from '../Models/pfj-tiles-details';
import { PFJTotal,BetterOf,TotalRetail,RetailMinus,Funded,CCC } from '../Models/pfj-tiles';
export interface ICustPriceDetailsVersusProp {
    vsTgLeft:string;
    vsTgLeftPositive:string;
    vsTgRight:string;
    vsLyLeft:string;
    vsLyLeftPositive:string;
    vsLyRight:string;
}

export interface ICustPriceTilesDetails
{
    grossProfitDollars:GrossProfitDollars;
    volume:Volume;
    margin:Margin;
    mixPercentage:MixPercentage;
}

export interface ICustPriceMixPercentage
{
    mixActual:string;
    mixTarget:string;
    mixVsLy:string;
    mixVsLyPositive:string;
}

export interface ICustomerPriceDetails
{
    temporalPeriod:string;  
    pfjTotal:PFJTotal;
    betterOf:BetterOf;
    totalRetail:TotalRetail;
    retailMinus:RetailMinus;
    funded:Funded;
    ccc:CCC;
}