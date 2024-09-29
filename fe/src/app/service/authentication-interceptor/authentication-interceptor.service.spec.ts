import { TestBed } from '@angular/core/testing';
import {AuthenticationInterceptorService} from "./authentication-interceptor.service";


describe('AuthenticationInterceptorService', () => {
  let service: AuthenticationInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthenticationInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
