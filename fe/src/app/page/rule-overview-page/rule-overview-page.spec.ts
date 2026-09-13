import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleOverviewPage } from './rule-overview-page';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('RuleOverviewPage', () => {
  let underTest: RuleOverviewPage;
  let fixture: ComponentFixture<RuleOverviewPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleOverviewPage],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleOverviewPage);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
