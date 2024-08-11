import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingCreationComponent } from './spending-creation.component';

describe('SpendingCreationComponent', () => {
  let component: SpendingCreationComponent;
  let fixture: ComponentFixture<SpendingCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
