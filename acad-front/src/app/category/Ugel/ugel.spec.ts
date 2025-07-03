import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Ugel } from './ugel';

describe('UgelComponent', () => {
  let component: Ugel;
  let fixture: ComponentFixture<Ugel>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Ugel] // porque es un standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(Ugel);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the Ugel component', () => {
    expect(component).toBeTruthy();
  });
});
