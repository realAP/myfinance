import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedSquaresComponent } from './connected-squares.component';
import {provideHttpClient} from '@angular/common/http';
import {provideHttpClientTesting} from '@angular/common/http/testing';
import {provideRouter} from '@angular/router';
import {MessageService} from 'primeng/api';

describe('ConnectedSquaresComponent', () => {
  let component: ConnectedSquaresComponent;
  let fixture: ComponentFixture<ConnectedSquaresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConnectedSquaresComponent],
      providers: [provideHttpClient(), provideHttpClientTesting(), provideRouter([]), MessageService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConnectedSquaresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
