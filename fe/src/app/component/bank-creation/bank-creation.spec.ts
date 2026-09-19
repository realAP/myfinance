import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankCreation } from './bank-creation';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('BankCreation', () => {
  let underTest: BankCreation;
  let fixture: ComponentFixture<BankCreation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankCreation],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankCreation);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
