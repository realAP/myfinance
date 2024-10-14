import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOverviewPageComponent } from './income-overview-page.component';

describe('IncomeOverviewPageComponent', () => {
  let component: IncomeOverviewPageComponent;
  let fixture: ComponentFixture<IncomeOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeOverviewPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
