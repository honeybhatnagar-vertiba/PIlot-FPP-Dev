import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PfjoverViewComponent } from './Components/pfjover-view/pfjover-view.component';
import { RetailPricingComponent } from './Components/retail-pricing/retail-pricing.component';
import { executeViewHooks } from '@angular/core/src/render3/instructions';
import { ExecutiveReportingComponent } from './Components/executive-reporting/executive-reporting.component';
import { CustomerPricingComponent } from './Components/customer-pricing/customer-pricing.component';

const appRoutes: Routes = [
    { path: '', component: PfjoverViewComponent },
    { path: 'pfjover-view', component: PfjoverViewComponent },
    { path: 'retail-pricing', component: RetailPricingComponent },
    { path: 'executive-reporting', component: ExecutiveReportingComponent },
    { path: 'customer-pricing', component: CustomerPricingComponent },
];

@NgModule({
    imports: [
        RouterModule.forRoot(appRoutes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule { }
