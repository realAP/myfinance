import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackofficePage } from './backoffice-page';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('BackofficePage', () => {
  let underTest: BackofficePage;
  let fixture: ComponentFixture<BackofficePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackofficePage],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackofficePage);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
