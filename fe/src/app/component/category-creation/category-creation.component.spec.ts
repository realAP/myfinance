import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCreationComponent } from './category-creation.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('CategoryCreationComponent', () => {
  let component: CategoryCreationComponent;
  let fixture: ComponentFixture<CategoryCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryCreationComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CategoryCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
