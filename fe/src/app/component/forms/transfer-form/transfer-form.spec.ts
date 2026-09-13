import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferForm } from './transfer-form';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('TransferCreationComponent', () => {
  let underTest: TransferForm;
  let fixture: ComponentFixture<TransferForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransferForm],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferForm);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
