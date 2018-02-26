import { ICustPriceDetailsVersusProp,ICustPriceMixPercentage } from '../Interface/pfj-interfaces';
export class GrossProfitDollars implements ICustPriceDetailsVersusProp {
    vsTgLeft: string;
    vsTgLeftPositive: string;
    vsTgRight: string;
    vsLyLeft: string;
    vsLyLeftPositive: string;
    vsLyRight: string;
}
export class Volume implements ICustPriceDetailsVersusProp {
    vsTgLeft: string;
    vsTgLeftPositive: string;
    vsTgRight: string;
    vsLyLeft: string;
    vsLyLeftPositive: string;
    vsLyRight: string;
}
export class Margin implements ICustPriceDetailsVersusProp {
    vsTgLeft: string;
    vsTgLeftPositive: string;
    vsTgRight: string;
    vsLyLeft: string;
    vsLyLeftPositive: string;
    vsLyRight: string;
}
export class MixPercentage implements ICustPriceMixPercentage {
    mixActual: string;
    mixTarget: string;
    mixVsLy: string;
    mixVsLyPositive: string;
}