import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InstitucionComponent } from './institucion'; // Asegúrate que la ruta sea correcta

describe('InstitucionComponent', () => {
  let component: InstitucionComponent;
  let fixture: ComponentFixture<InstitucionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstitucionComponent] // standalone component
    }).compileComponents();

    fixture = TestBed.createComponent(InstitucionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
