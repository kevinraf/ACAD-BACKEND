import { ComponentFixture, TestBed } from '@angular/core/testing';
import {Apoderado} from './apoderado';

describe('ApoderadoModel', () => {
  let component: Apoderado;
  let fixture: ComponentFixture<Apoderado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Apoderado] // standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(Apoderado);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
