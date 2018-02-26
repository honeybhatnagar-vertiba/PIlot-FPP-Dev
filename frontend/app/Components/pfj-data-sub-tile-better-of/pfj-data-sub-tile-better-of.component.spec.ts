import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfjDataSubTileBetterOfComponent } from './pfj-data-sub-tile-better-of.component';

describe('PfjDataSubTileBetterOfComponent', () => {
  let component: PfjDataSubTileBetterOfComponent;
  let fixture: ComponentFixture<PfjDataSubTileBetterOfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfjDataSubTileBetterOfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfjDataSubTileBetterOfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
