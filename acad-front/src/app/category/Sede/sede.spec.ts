import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Sede } from './sede';
import { SedeService } from '../../core/service/Sede/sede.service';
import { of } from 'rxjs';
import { SedeModel } from '../models/sede-model';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('SedeComponent', () => {
  let component: Sede;
  let fixture: ComponentFixture<Sede>;
  let sedeService: jasmine.SpyObj<SedeService>;

  const mockSedes: SedeModel[] = [
    { idSede: 1, nombreSede: 'Sede Central' },
    { idSede: 2, nombreSede: 'Sede Sur' }
  ];

  beforeEach(async () => {
    const sedeServiceSpy = jasmine.createSpyObj('SedeService', ['getSedes', 'delete$']);

    await TestBed.configureTestingModule({
      declarations: [],
      imports: [RouterTestingModule, HttpClientTestingModule],
      providers: [
        { provide: SedeService, useValue: sedeServiceSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(Sede);
    component = fixture.componentInstance;
    sedeService = TestBed.inject(SedeService) as jasmine.SpyObj<SedeService>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load sedes on init', () => {
    sedeService.getSedes.and.returnValue(of(mockSedes));
    component.ngOnInit();
    expect(component.sedes.length).toBe(2);
    expect(component.sedes).toEqual(mockSedes);
  });

  it('should delete a sede', () => {
    spyOn(window, 'confirm').and.returnValue(true);
    component.sedes = [...mockSedes];
    sedeService.delete$.and.returnValue(of(undefined));

    component.eliminar(1);
    expect(sedeService.delete$).toHaveBeenCalledWith(1);
  });
});
