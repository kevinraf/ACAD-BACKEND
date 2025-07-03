import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ApoderadoService } from './apoderado.service';
import { resources } from '../../resources/resources';
import {ApoderadoModel} from '../../../category/models/apoderado-model';


describe('ApoderadoService', () => {
  let service: ApoderadoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApoderadoService]
    });
    service = TestBed.inject(ApoderadoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get all apoderados', () => {
    const dummyApoderados: ApoderadoModel[] = [
      { nombres: 'Juan', apellidoPaterno: 'Pérez', apellidoMaterno: 'Gómez', fechaNacimiento: '', direccion: '', rolFamilia: '' },
      { nombres: 'Ana', apellidoPaterno: 'Ramírez', apellidoMaterno: 'Soto', fechaNacimiento: '', direccion: '', rolFamilia: '' }
    ];

    service.getApoderados().subscribe((apoderados) => {
      expect(apoderados.length).toBe(2);
      expect(apoderados).toEqual(dummyApoderados);
    });

    const req = httpMock.expectOne(resources.apoderados.apoderado);
    expect(req.request.method).toBe('GET');
    req.flush(dummyApoderados);
  });

  it('should get an apoderado by id', () => {
    const mockApoderado: ApoderadoModel = {
      idApoderado: 1,
      nombres: 'Pedro',
      apellidoPaterno: 'López',
      apellidoMaterno: 'Martínez',
      fechaNacimiento: '1980-01-01',
      direccion: 'Calle Falsa 123',
      rolFamilia: 'Padre'
    };

    service.getById$(1).subscribe((apoderado) => {
      expect(apoderado).toEqual(mockApoderado);
    });

    const req = httpMock.expectOne(`${resources.apoderados.apoderado}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(mockApoderado);
  });

  it('should create an apoderado', () => {
    const newApoderado: ApoderadoModel = {
      nombres: 'Lucía',
      apellidoPaterno: 'Fernández',
      apellidoMaterno: 'Silva',
      fechaNacimiento: '1990-05-10',
      direccion: 'Av. Principal',
      rolFamilia: 'Madre'
    };

    service.create$(newApoderado).subscribe((response) => {
      expect(response).toEqual(newApoderado);
    });

    const req = httpMock.expectOne(resources.apoderados.apoderado);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newApoderado);
    req.flush(newApoderado);
  });

  it('should update an apoderado', () => {
    const updatedApoderado: ApoderadoModel = {
      idApoderado: 2,
      nombres: 'Carlos',
      apellidoPaterno: 'García',
      apellidoMaterno: 'Reyes',
      fechaNacimiento: '1992-03-15',
      direccion: 'Jr. Lima 456',
      rolFamilia: 'Tío'
    };

    service.update$(2, updatedApoderado).subscribe((response) => {
      expect(response).toEqual(updatedApoderado);
    });

    const req = httpMock.expectOne(`${resources.apoderados.apoderado}/2`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedApoderado);
    req.flush(updatedApoderado);
  });

  it('should delete an apoderado', () => {
    service.delete$(3).subscribe((response) => {
      expect(response).toBeNull();
    });

    const req = httpMock.expectOne(`${resources.apoderados.apoderado}/3`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
