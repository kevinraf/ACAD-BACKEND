import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Matricula } from './matricula'; // Asegúrate de que la ruta sea correcta

describe('MatriculaComponent', () => {
  let component: Matricula;
  let fixture: ComponentFixture<Matricula>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Matricula] // componente standalone
    }).compileComponents();

    fixture = TestBed.createComponent(Matricula);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
