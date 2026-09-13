import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackofficePageComponent } from './backoffice-page';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('BackofficePageComponent', () => {
  let underTest: BackofficePageComponent;
  let fixture: ComponentFixture<BackofficePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackofficePageComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackofficePageComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
