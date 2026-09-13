import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingFormComponent } from './spending-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('SpendingCreationComponent', () => {
  let underTest: SpendingFormComponent;
  let fixture: ComponentFixture<SpendingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingFormComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingFormComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
