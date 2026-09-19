import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferOverviewPage } from './transfer-overview-page';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('TransferOverviewPage', () => {
  let underTest: TransferOverviewPage;
  let fixture: ComponentFixture<TransferOverviewPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransferOverviewPage],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferOverviewPage);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
