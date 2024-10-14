import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryCreationComponent } from './category-creation.component';

describe('CategoryCreationComponent', () => {
  let component: CategoryCreationComponent;
  let fixture: ComponentFixture<CategoryCreationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryCreationComponent]
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
