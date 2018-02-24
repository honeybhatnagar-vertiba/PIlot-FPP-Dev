import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetailPricingComponent } from './retail-pricing.component';

describe('RetailPricingComponent', () => {
  let component: RetailPricingComponent;
  let fixture: ComponentFixture<RetailPricingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetailPricingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetailPricingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
