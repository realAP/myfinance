import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedSquares } from './connected-squares';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('ConnectedSquares', () => {
  let underTest: ConnectedSquares;
  let fixture: ComponentFixture<ConnectedSquares>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConnectedSquares],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConnectedSquares);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
