import { ICustPriceTilesDetails } from '../Interface/pfj-interfaces'
import { GrossProfitDollar,Margin,Volume,MixPercentage } from './pfj-tiles-details';
export class PFJTotal implements ICustPriceTilesDetails {
    mixPercentage: MixPercentage;
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    totalGAL:string;
    totalTarget:string;
}
export class BetterOf implements ICustPriceTilesDetails {
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
    buyingPerfActual:string;
    buyingPerfTarget:string;
    effPumpFeeActual:string;
    effPumpFeeTarget:string;
}
export class TotalRetail implements ICustPriceTilesDetails {
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
}
export class RetailMinus implements ICustPriceTilesDetails {
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    mixPercentage:MixPercentage;
    rmDiscountActual:string;
    rmDiscountTarget:string;
}
export class Funded implements ICustPriceTilesDetails {
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    mixPercentage: MixPercentage;
}
export class CCC implements ICustPriceTilesDetails {
    grossProfitDollar: GrossProfitDollar;
    volume: Volume;
    margin: Margin;
    mixPercentage: MixPercentage;
}
