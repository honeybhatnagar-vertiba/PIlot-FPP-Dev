import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExecutiveReportingComponent } from './executive-reporting.component';

describe('ExecutiveReportingComponent', () => {
  let component: ExecutiveReportingComponent;
  let fixture: ComponentFixture<ExecutiveReportingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExecutiveReportingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExecutiveReportingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
