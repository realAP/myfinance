import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankCreationComponent } from './bank-creation';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('BankCreationComponent', () => {
  let underTest: BankCreationComponent;
  let fixture: ComponentFixture<BankCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankCreationComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankCreationComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
