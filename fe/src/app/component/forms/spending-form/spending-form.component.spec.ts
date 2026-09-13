import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpendingFormComponent } from './spending-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('SpendingCreationComponent', () => {
  let component: SpendingFormComponent;
  let fixture: ComponentFixture<SpendingFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpendingFormComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpendingFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
