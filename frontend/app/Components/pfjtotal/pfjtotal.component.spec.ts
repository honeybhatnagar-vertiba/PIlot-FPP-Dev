import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfjtotalComponent } from './pfjtotal.component';

describe('PfjtotalComponent', () => {
  let component: PfjtotalComponent;
  let fixture: ComponentFixture<PfjtotalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfjtotalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfjtotalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
