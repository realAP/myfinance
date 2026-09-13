import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpaceFormComponent } from './space-form.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('SpaceCreationComponent', () => {
  let underTest: SpaceFormComponent;
  let fixture: ComponentFixture<SpaceFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpaceFormComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpaceFormComponent);
    underTest = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(underTest).toBeTruthy();
  });
});
