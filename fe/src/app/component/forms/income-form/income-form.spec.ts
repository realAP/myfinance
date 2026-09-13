import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeForm } from './income-form';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('IncomeForm', () => {
  let underTest: IncomeForm;
  let fixture: ComponentFixture<IncomeForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeForm],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeForm);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
