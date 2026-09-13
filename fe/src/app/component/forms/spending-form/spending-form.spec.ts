import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingForm } from './spending-form';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('SpendingCreationComponent', () => {
  let underTest: SpendingForm;
  let fixture: ComponentFixture<SpendingForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingForm],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingForm);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
