import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferOverviewPageComponent } from './transfer-overview-page.component';

describe('TransferOverviewPageComponent', () => {
  let component: TransferOverviewPageComponent;
  let fixture: ComponentFixture<TransferOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransferOverviewPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
