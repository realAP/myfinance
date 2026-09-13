import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedSquaresComponent } from './connected-squares';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('ConnectedSquaresComponent', () => {
  let underTest: ConnectedSquaresComponent;
  let fixture: ComponentFixture<ConnectedSquaresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConnectedSquaresComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConnectedSquaresComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
