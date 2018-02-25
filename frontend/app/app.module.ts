import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './Components/header/header.component';
import { SectionContainerComponent } from './Components/section-container/section-container.component';
import { FooterComponent } from './Components/footer/footer.component';
import { PfjoverViewComponent } from './Components/pfjover-view/pfjover-view.component';
import { RetailPricingComponent } from './Components/retail-pricing/retail-pricing.component';
import { ExecutiveReportingComponent } from './Components/executive-reporting/executive-reporting.component';
import { CustomerPricingComponent } from './Components/customer-pricing/customer-pricing.component';

import {AppRoutingModule} from './app-routing.module';
import { ServiceConsumer } from './service-consumer.service';
import { RepositoryService } from './repository.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SectionContainerComponent,
    FooterComponent,
    PfjoverViewComponent,
    RetailPricingComponent,
    ExecutiveReportingComponent,
    CustomerPricingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule
  ],
  providers: [ServiceConsumer,RepositoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
