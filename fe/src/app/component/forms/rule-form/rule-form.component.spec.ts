import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleFormComponent } from './rule-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('RuleCreationComponent', () => {
  let component: RuleFormComponent;
  let fixture: ComponentFixture<RuleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleFormComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
