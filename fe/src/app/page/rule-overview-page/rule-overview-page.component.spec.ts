import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleOverviewPageComponent } from './rule-overview-page.component';

describe('RuleOverviewPageComponent', () => {
  let component: RuleOverviewPageComponent;
  let fixture: ComponentFixture<RuleOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleOverviewPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
