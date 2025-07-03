import { ComponentFixture, TestBed } from '@angular/core/testing';
import {NewRequisitoComponent} from './new/new-requisito.component';

describe('RequisitoComponent', () => {
  let component: NewRequisitoComponent;
  let fixture: ComponentFixture<NewRequisitoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewRequisitoComponent] // standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(NewRequisitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
