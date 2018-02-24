import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfjoverViewComponent } from './pfjover-view.component';

describe('PfjoverViewComponent', () => {
  let component: PfjoverViewComponent;
  let fixture: ComponentFixture<PfjoverViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfjoverViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfjoverViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
