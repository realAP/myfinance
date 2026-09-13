import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOverviewPageComponent } from './income-overview-page.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('IncomeOverviewPageComponent', () => {
  let component: IncomeOverviewPageComponent;
  let fixture: ComponentFixture<IncomeOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeOverviewPageComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeOverviewPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
