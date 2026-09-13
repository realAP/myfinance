import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackofficePageComponent } from './backoffice-page.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('BackofficePageComponent', () => {
  let component: BackofficePageComponent;
  let fixture: ComponentFixture<BackofficePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BackofficePageComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackofficePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
