import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SedeService } from './sede.service';
import { SedeModel } from '../../../category/models/sede-model';
import { resources } from '../../resources/resources';

describe('SedeService', () => {
  let service: SedeService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SedeService]
    });
    service = TestBed.inject(SedeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all sedes', () => {
    const mockSedes: SedeModel[] = [
      { idSede: 1, nombreSede: 'Sede Norte' },
      { idSede: 2, nombreSede: 'Sede Sur' }
    ];

    service.getSedes().subscribe((sedes) => {
      expect(sedes.length).toBe(2);
      expect(sedes).toEqual(mockSedes);
    });

    const req = httpMock.expectOne(resources.sedes.sede);
    expect(req.request.method).toBe('GET');
    req.flush(mockSedes);
  });

  it('should get sede by ID', () => {
    const mockSede: SedeModel = { idSede: 1, nombreSede: 'Sede Central' };

    service.getById$(1).subscribe((sede) => {
      expect(sede).toEqual(mockSede);
    });

    const req = httpMock.expectOne(`${resources.sedes.sede}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockSede);
  });

  it('should create a sede', () => {
    const newSede: SedeModel = { nombreSede: 'Nueva Sede' };

    service.create$(newSede).subscribe((response) => {
      expect(response).toEqual(newSede);
    });

    const req = httpMock.expectOne(resources.sedes.sede);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newSede);
    req.flush(newSede);
  });

  it('should update a sede', () => {
    const updatedSede: SedeModel = { idSede: 1, nombreSede: 'Sede Actualizada' };

    service.update$(1, updatedSede).subscribe((response) => {
      expect(response).toEqual(updatedSede);
    });

    const req = httpMock.expectOne(`${resources.sedes.sede}/1`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedSede);
    req.flush(updatedSede);
  });

  it('should delete a sede', () => {
    service.delete$(1).subscribe((response) => {
      expect(response).toBeUndefined();
    });

    const req = httpMock.expectOne(`${resources.sedes.sede}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
