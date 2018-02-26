import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PfjDataTileComponent } from './pfj-data-tile.component';

describe('PfjDataTileComponent', () => {
  let component: PfjDataTileComponent;
  let fixture: ComponentFixture<PfjDataTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PfjDataTileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PfjDataTileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
