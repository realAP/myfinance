import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeFormComponent } from './income-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('IncomeFormComponent', () => {
  let component: IncomeFormComponent;
  let fixture: ComponentFixture<IncomeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeFormComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
