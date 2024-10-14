import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankCreationComponent } from './bank-creation.component';

describe('BankCreationComponent', () => {
  let component: BankCreationComponent;
  let fixture: ComponentFixture<BankCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
