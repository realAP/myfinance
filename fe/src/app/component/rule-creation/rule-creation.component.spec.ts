import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleCreationComponent } from './rule-creation.component';

describe('RuleCreationComponent', () => {
  let component: RuleCreationComponent;
  let fixture: ComponentFixture<RuleCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
