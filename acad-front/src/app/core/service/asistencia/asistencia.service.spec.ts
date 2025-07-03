import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AsistenciaService } from './asistencia.service';
import { resources } from '../../resources/resources';
import { AsistenciaModel } from '../../../category/models/asistencia-model';

describe('AsistenciaService', () => {
  let service: AsistenciaService;
  let httpMock: HttpTestingController;
  const url = resources.asistencias.asistencia;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AsistenciaService]
    });
    service = TestBed.inject(AsistenciaService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all asistencias', () => {
    const dummy: AsistenciaModel[] = [
      { idAsistencia: 1, estadoAsistencia: 'PRESENTE' },
      { idAsistencia: 2, estadoAsistencia: 'FALTA' }
    ];

    service.getAll$().subscribe(list => {
      expect(list.length).toBe(2);
      expect(list).toEqual(dummy);
    });

    const req = httpMock.expectOne(url);
    expect(req.request.method).toBe('GET');
    req.flush(dummy);
  });

  it('should get asistencia by id', () => {
    const mock: AsistenciaModel = { idAsistencia: 5, estadoAsistencia: 'TARDANZA' };

    service.getById$(5).subscribe(res => {
      expect(res).toEqual(mock);
    });

    const req = httpMock.expectOne(`${url}/5`);
    expect(req.request.method).toBe('GET');
    req.flush(mock);
  });

  it('should create an asistencia', () => {
    const newItem: AsistenciaModel = { estadoAsistencia: 'PRESENTE' };

    service.create$(newItem).subscribe(res => {
      expect(res).toEqual(newItem);
    });

    const req = httpMock.expectOne(url);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newItem);
    req.flush(newItem);
  });

  it('should update an asistencia', () => {
    const updated: AsistenciaModel = { idAsistencia: 3, estadoAsistencia: 'FALTA' };

    service.update$(3, updated).subscribe(res => {
      expect(res).toEqual(updated);
    });

    const req = httpMock.expectOne(`${url}/3`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updated);
    req.flush(updated);
  });

  it('should delete an asistencia', () => {
    service.delete$(7).subscribe(res => {
      expect(res).toBeNull();
    });

    const req = httpMock.expectOne(`${url}/7`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
