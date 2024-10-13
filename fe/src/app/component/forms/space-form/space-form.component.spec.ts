import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpaceFormComponent } from './space-form.component';

describe('SpaceCreationComponent', () => {
  let component: SpaceFormComponent;
  let fixture: ComponentFixture<SpaceFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SpaceFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpaceFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
