import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingFormComponent } from './spending-form.component';

describe('SpendingCreationComponent', () => {
  let component: SpendingFormComponent;
  let fixture: ComponentFixture<SpendingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
