import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpaceForm } from './space-form';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('SpaceCreationComponent', () => {
  let underTest: SpaceForm;
  let fixture: ComponentFixture<SpaceForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpaceForm],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpaceForm);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
