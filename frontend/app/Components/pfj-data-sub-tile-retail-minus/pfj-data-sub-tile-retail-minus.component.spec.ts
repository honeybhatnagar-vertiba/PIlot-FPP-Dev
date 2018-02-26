import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfjDataSubTileRetailMinusComponent } from './pfj-data-sub-tile-retail-minus.component';

describe('PfjDataSubTileRetailMinusComponent', () => {
  let component: PfjDataSubTileRetailMinusComponent;
  let fixture: ComponentFixture<PfjDataSubTileRetailMinusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfjDataSubTileRetailMinusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfjDataSubTileRetailMinusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
