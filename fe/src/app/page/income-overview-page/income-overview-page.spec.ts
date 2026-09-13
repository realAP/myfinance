import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncomeOverviewPage } from './income-overview-page';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('IncomeOverviewPage', () => {
  let underTest: IncomeOverviewPage;
  let fixture: ComponentFixture<IncomeOverviewPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncomeOverviewPage],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncomeOverviewPage);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
