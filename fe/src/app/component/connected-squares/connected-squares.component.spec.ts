import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConnectedSquaresComponent } from './connected-squares.component';

describe('ConnectedSquaresComponent', () => {
  let component: ConnectedSquaresComponent;
  let fixture: ComponentFixture<ConnectedSquaresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConnectedSquaresComponent]
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
