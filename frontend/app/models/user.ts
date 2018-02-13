
export class User {
   constructor(
      public id: string,
      public category: string,
	  public percent1: string,
      public amount1: string,
	  public percent2: string,
	  public amount2: string,
	  public percent_gallons: string,
	  public amount_gallons: string,
	  public percent_gallons1: string,
	  public amount_gallons1: string,
	  public actual: string,
	  public target: string,
	  public vs_ly: string
   ) {}
}
