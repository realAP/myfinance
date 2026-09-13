import { TestBed } from '@angular/core/testing';

import { AuthService } from './auth';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('AuthService', () => {
  let underTest: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    });
    underTest = TestBed.inject(AuthService);
  });

  it('should be created', () => {
    expect(underTest).toBeTruthy();
  });
});
