import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingCreationPageComponent } from './spending-creation-page.component';

describe('SpendingCreationPageComponent', () => {
  let component: SpendingCreationPageComponent;
  let fixture: ComponentFixture<SpendingCreationPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingCreationPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingCreationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
