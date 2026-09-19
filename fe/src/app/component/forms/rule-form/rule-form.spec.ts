import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RuleForm } from './rule-form';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('RuleCreationComponent', () => {
  let underTest: RuleForm;
  let fixture: ComponentFixture<RuleForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RuleForm],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RuleForm);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
