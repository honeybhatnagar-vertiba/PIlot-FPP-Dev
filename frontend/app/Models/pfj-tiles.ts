import { ICustPriceTilesDetails } from '../Interface/pfj-interfaces'
import { GrossProfitDollars,Margin,Volume,MixPercentage } from './pfj-tiles-details';
export class PFJTotal implements ICustPriceTilesDetails {
    mixPercentage: MixPercentage;
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    totalGAL:string;
    totalTarget:string;
}
export class BetterOf implements ICustPriceTilesDetails {
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
    buyingPerfActual:string;
    buyingPerfTarget:string;
    effPumpFeeActual:string;
    effPumpFeeTarget:string;
}
export class TotalRetail implements ICustPriceTilesDetails {
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
}
export class RetailMinus implements ICustPriceTilesDetails {
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
    rmDiscountActual:string;
    rmDiscountTarget:string;
}
export class Funded implements ICustPriceTilesDetails {
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    mixPercentage: MixPercentage;
}
export class CCC implements ICustPriceTilesDetails {
    grossProfitDollars: GrossProfitDollars;
    volume: Volume;
    margin: Margin;
    mixPercentage: MixPercentage;
}
