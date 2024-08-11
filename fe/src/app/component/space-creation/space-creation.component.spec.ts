import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpaceCreationComponent } from './space-creation.component';

describe('SpaceCreationComponent', () => {
  let component: SpaceCreationComponent;
  let fixture: ComponentFixture<SpaceCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpaceCreationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpaceCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
