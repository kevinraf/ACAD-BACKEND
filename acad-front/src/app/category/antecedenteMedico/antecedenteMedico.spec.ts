import { ComponentFixture, TestBed } from '@angular/core/testing';
import {AntecedenteMedico} from './antecedenteMedico';

describe('AntecedenteMedicoModel', () => {
  let component: AntecedenteMedico;
  let fixture: ComponentFixture<AntecedenteMedico>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AntecedenteMedico] // standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(AntecedenteMedico);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
