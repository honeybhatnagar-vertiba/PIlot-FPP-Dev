import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerPricingComponent } from './customer-pricing.component';

describe('CustomerPricingComponent', () => {
  let component: CustomerPricingComponent;
  let fixture: ComponentFixture<CustomerPricingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerPricingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerPricingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
