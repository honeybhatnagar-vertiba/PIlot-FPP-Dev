import { Component, OnInit } from '@angular/core';
import { ServiceConsumer } from '../../service-consumer.service';
import { RepositoryService } from '../../repository.service';
import { PfjOverviewOperations } from '../../Models/pfjoverview-operations';
import { CustomerPriceDetails } from '../../Models/customer-price-details';
@Component({
  selector: 'app-pfjover-view',
  templateUrl: './pfjover-view.component.html',
  styleUrls: ['./pfjover-view.component.css']
})
export class PfjoverViewComponent implements OnInit {
  selectedCustPricingDetails : CustomerPriceDetails;
  constructor(private serviceConsumer: ServiceConsumer ,private repository : RepositoryService ) {
   }

  ngOnInit() {
   if(this.repository.customerPricingDetails.length<=0)
    {
      //Get customer pricing details from service.
      this.serviceConsumer.GetCustomerPricingDetails()
      .subscribe(data=>this.PopulateCustomerPricingDetails(data),error=>console.log(error));
    }
    else
    {
      console.log(this.repository.selectedDataPeriod);
      this.ProcessSelectedCustomerPricingDetails(this.repository.selectedDataPeriod);
    }
    /*let s = '{"temporalPeriod":"MTD","pfjTotal":{"grossProfitDollars":{"vsTgLeft":59,"vsTgLeftPositive":true,"vsTgRight":11,"vsLyLeft":5,"vsLyLeftPositive":true,"vsLyRight":65},"volume":{"vsTgLeft":60,"vsTgLeftPositive":true,"vsTgRight":467,"vsLyLeft":70,"vsLyLeftPositive":true,"vsLyRight":2087},"margin":{"vsTgLeft":-25,"vsTgLeftPositive":false,"vsTgRight":95,"vsLyLeft":59,"vsLyLeftPositive":true,"vsLyRight":10},"totalGAL":3,"totalTarget":13},"betterOf":{"grossProfitDollars":{"vsTgLeft":-32,"vsTgLeftPositive":false,"vsTgRight":80,"vsLyLeft":29,"vsLyLeftPositive":true,"vsLyRight":17},"volume":{"vsTgLeft":55,"vsTgLeftPositive":true,"vsTgRight":156,"vsLyLeft":56,"vsLyLeftPositive":true,"vsLyRight":165},"margin":{"vsTgLeft":42,"vsTgLeftPositive":true,"vsTgRight":10,"vsLyLeft":-18,"vsLyLeftPositive":false,"vsLyRight":71},"mixPercentage":{"mixActual":100,"mixTarget":270,"mixVsLy":-87,"mixVsLyPositive":false},"buyingPerfActual":3,"buyingPerfTarget":92,"effPumpFeeActual":23,"effPumpFeeTarget":90},"totalRetail":{"grossProfitDollars":{"vsTgLeft":-1,"vsTgLeftPositive":false,"vsTgRight":71,"vsLyLeft":40,"vsLyLeftPositive":true,"vsLyRight":29},"volume":{"vsTgLeft":-11,"vsTgLeftPositive":false,"vsTgRight":-87,"vsLyLeft":-56,"vsLyLeftPositive":false,"vsLyRight":-97},"margin":{"vsTgLeft":5,"vsTgLeftPositive":true,"vsTgRight":84,"vsLyLeft":85,"vsLyLeftPositive":true,"vsLyRight":5},"mixPercentage":{"mixActual":100,"mixTarget":97,"mixVsLy":-99,"mixVsLyPositive":false}},"retailMinus":{"grossProfitDollars":{"vsTgLeft":-53,"vsTgLeftPositive":false,"vsTgRight":89,"vsLyLeft":-49,"vsLyLeftPositive":false,"vsLyRight":86},"volume":{"vsTgLeft":-57,"vsTgLeftPositive":false,"vsTgRight":-59,"vsLyLeft":-58,"vsLyLeftPositive":false,"vsLyRight":-60},"margin":{"vsTgLeft":-48,"vsTgLeftPositive":false,"vsTgRight":80,"vsLyLeft":7,"vsLyLeftPositive":true,"vsLyRight":25},"mixPercentage":{"mixActual":100,"mixTarget":743,"mixVsLy":-98,"mixVsLyPositive":false},"rmDiscountActual":0,"rmDiscountTarget":0},"funded":{"grossProfitDollars":{"vsTgLeft":13,"vsTgLeftPositive":true,"vsTgRight":8,"vsLyLeft":-56,"vsLyLeftPositive":false,"vsLyRight":78},"volume":{"vsTgLeft":-4,"vsTgLeftPositive":false,"vsTgRight":-10,"vsLyLeft":-15,"vsLyLeftPositive":false,"vsLyRight":-30},"margin":{"vsTgLeft":-41,"vsTgLeftPositive":false,"vsTgRight":88,"vsLyLeft":24,"vsLyLeftPositive":true,"vsLyRight":22},"mixPercentage":{"mixActual":100,"mixTarget":293,"mixVsLy":-96,"mixVsLyPositive":false}},"ccc":{"grossProfitDollars":{"vsTgLeft":32,"vsTgLeftPositive":true,"vsTgRight":64,"vsLyLeft":86,"vsLyLeftPositive":true,"vsLyRight":10},"volume":{"vsTgLeft":-56,"vsTgLeftPositive":false,"vsTgRight":-57,"vsLyLeft":-7,"vsLyLeftPositive":false,"vsLyRight":-14},"margin":{"vsTgLeft":39,"vsTgLeftPositive":true,"vsTgRight":37,"vsLyLeft":12,"vsLyLeftPositive":true,"vsLyRight":63},"mixPercentage":{"mixActual":100,"mixTarget":754,"mixVsLy":-96,"mixVsLyPositive":false}}}';
     // let s=JSON.parse(JSON.stringify("{'temporalPeriod':'MTD','pfjTotal':{'grossProfitDollars':{'vsTgLeft':59,'vsTgLeftPositive':true,'vsTgRight':11,'vsLyLeft':5,'vsLyLeftPositive':true,'vsLyRight':65},'volume':{'vsTgLeft':60,'vsTgLeftPositive':true,'vsTgRight':467,'vsLyLeft':70,'vsLyLeftPositive':true,'vsLyRight':2087},'margin':{'vsTgLeft':-25,'vsTgLeftPositive':false,'vsTgRight':95,'vsLyLeft':59,'vsLyLeftPositive':true,'vsLyRight':10},'totalGAL':3,'totalTarget':13},'betterOf':{'grossProfitDollars':{'vsTgLeft':-32,'vsTgLeftPositive':false,'vsTgRight':80,'vsLyLeft':29,'vsLyLeftPositive':true,'vsLyRight':17},'volume':{'vsTgLeft':55,'vsTgLeftPositive':true,'vsTgRight':156,'vsLyLeft':56,'vsLyLeftPositive':true,'vsLyRight':165},'margin':{'vsTgLeft':42,'vsTgLeftPositive':true,'vsTgRight':10,'vsLyLeft':-18,'vsLyLeftPositive':false,'vsLyRight':71},'mixPercentage':{'mixActual':100,'mixTarget':270,'mixVsLy':-87,'mixVsLyPositive':false},'buyingPerfActual':3,'buyingPerfTarget':92,'effPumpFeeActual':23,'effPumpFeeTarget':90},'totalRetail':{'grossProfitDollars':{'vsTgLeft':-1,'vsTgLeftPositive':false,'vsTgRight':71,'vsLyLeft':40,'vsLyLeftPositive':true,'vsLyRight':29},'volume':{'vsTgLeft':-11,'vsTgLeftPositive':false,'vsTgRight':-87,'vsLyLeft':-56,'vsLyLeftPositive':false,'vsLyRight':-97},'margin':{'vsTgLeft':5,'vsTgLeftPositive':true,'vsTgRight':84,'vsLyLeft':85,'vsLyLeftPositive':true,'vsLyRight':5},'mixPercentage':{'mixActual':100,'mixTarget':97,'mixVsLy':-99,'mixVsLyPositive':false}},'retailMinus':{'grossProfitDollars':{'vsTgLeft':-53,'vsTgLeftPositive':false,'vsTgRight':89,'vsLyLeft':-49,'vsLyLeftPositive':false,'vsLyRight':86},'volume':{'vsTgLeft':-57,'vsTgLeftPositive':false,'vsTgRight':-59,'vsLyLeft':-58,'vsLyLeftPositive':false,'vsLyRight':-60},'margin':{'vsTgLeft':-48,'vsTgLeftPositive':false,'vsTgRight':80,'vsLyLeft':7,'vsLyLeftPositive':true,'vsLyRight':25},'mixPercentage':{'mixActual':100,'mixTarget':743,'mixVsLy':-98,'mixVsLyPositive':false},'rmDiscountActual':0,'rmDiscountTarget':0},'funded':{'grossProfitDollars':{'vsTgLeft':13,'vsTgLeftPositive':true,'vsTgRight':8,'vsLyLeft':-56,'vsLyLeftPositive':false,'vsLyRight':78},'volume':{'vsTgLeft':-4,'vsTgLeftPositive':false,'vsTgRight':-10,'vsLyLeft':-15,'vsLyLeftPositive':false,'vsLyRight':-30},'margin':{'vsTgLeft':-41,'vsTgLeftPositive':false,'vsTgRight':88,'vsLyLeft':24,'vsLyLeftPositive':true,'vsLyRight':22},'mixPercentage':{'mixActual':100,'mixTarget':293,'mixVsLy':-96,'mixVsLyPositive':false}},'ccc':{'grossProfitDollars':{'vsTgLeft':32,'vsTgLeftPositive':true,'vsTgRight':64,'vsLyLeft':86,'vsLyLeftPositive':true,'vsLyRight':10},'volume':{'vsTgLeft':-56,'vsTgLeftPositive':false,'vsTgRight':-57,'vsLyLeft':-7,'vsLyLeftPositive':false,'vsLyRight':-14},'margin':{'vsTgLeft':39,'vsTgLeftPositive':true,'vsTgRight':37,'vsLyLeft':12,'vsLyLeftPositive':true,'vsLyRight':63},'mixPercentage':{'mixActual':100,'mixTarget':754,'mixVsLy':-96,'mixVsLyPositive':false}}}"));
      s = s.replace(/\\n/g, "\\n")  
               .replace(/\\'/g, "\\'")
               .replace(/\\"/g, '\\"')
               .replace(/\\&/g, "\\&")
               .replace(/\\r/g, "\\r")
               .replace(/\\t/g, "\\t")
               .replace(/\\b/g, "\\b")
               .replace(/\\f/g, "\\f");
// remove non-printable and other non-valid JSON chars
//s = s.replace(/[\u0000-\u0019]+/g,""); 
var o = JSON.parse(s);
      console.log(o);
      this.PopulateCustomerPricingDetails(o);*/
  }

  PopulateCustomerPricingDetails(data)
  {
    this.repository.customerPricingDetails = new PfjOverviewOperations().PopulateCustomerPricingDetails(data);
    this.ProcessSelectedCustomerPricingDetails("LCYTD");
    this.repository.selectedDataPeriod = "LCYTD";
  }

  ProcessSelectedCustomerPricingDetails(dataPeriod)
  {
    this.repository.selctedCustomerPricingDetails = this.repository.customerPricingDetails.find(c=>c.temporalPeriod.toUpperCase() == dataPeriod.toUpperCase());
    this.selectedCustPricingDetails = this.repository.selctedCustomerPricingDetails;
    this.repository.selectedDataPeriod = dataPeriod;
  }

}
