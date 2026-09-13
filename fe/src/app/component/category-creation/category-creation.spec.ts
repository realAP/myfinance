import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCreation } from './category-creation';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('CategoryCreation', () => {
  let underTest: CategoryCreation;
  let fixture: ComponentFixture<CategoryCreation>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryCreation],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryCreation);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
