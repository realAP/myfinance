import {ComponentFixture, TestBed} from '@angular/core/testing';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';

import {App} from './app';
import {MessageService} from 'primeng/api';

describe('App', () => {
  let fixture: ComponentFixture<App>;
  let underTest: App;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [App],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    }).compileComponents();

    fixture = TestBed.createComponent(App);
    underTest = fixture.componentInstance;
  });

  it('should create the app', () => {
    expect(underTest).toBeTruthy();
  });
});
