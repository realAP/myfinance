import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferCreationComponent } from './transfer-creation.component';

describe('TransferCreationComponent', () => {
  let component: TransferCreationComponent;
  let fixture: ComponentFixture<TransferCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransferCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
