import {ComponentFixture, TestBed} from '@angular/core/testing';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';

import {AppComponent} from './app';
import {MessageService} from 'primeng/api';

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let underTest: AppComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    underTest = fixture.componentInstance;
  });

  it('should create the app', () => {
    expect(underTest).toBeTruthy();
  });
});
