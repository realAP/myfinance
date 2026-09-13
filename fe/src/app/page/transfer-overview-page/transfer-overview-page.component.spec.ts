import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransferOverviewPageComponent } from './transfer-overview-page.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('TransferOverviewPageComponent', () => {
  let underTest: TransferOverviewPageComponent;
  let fixture: ComponentFixture<TransferOverviewPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TransferOverviewPageComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TransferOverviewPageComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
