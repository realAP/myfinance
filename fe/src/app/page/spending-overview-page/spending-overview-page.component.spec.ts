import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingOverviewPageComponent } from './spending-overview-page.component';

describe('SpendingOverviewPageComponent', () => {
  let component: SpendingOverviewPageComponent;
  let fixture: ComponentFixture<SpendingOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingOverviewPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
