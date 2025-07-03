import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UgelService } from './ugel.service';
import { resources } from '../../resources/resources';
import { UgelModel } from '../../../category/models/ugel-model';

describe('UgelService', () => {
  let service: UgelService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UgelService]
    });

    service = TestBed.inject(UgelService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch all UGELs', () => {
    const dummyUgeles: UgelModel[] = [
      { idUgel: 1, nombreDeLaUgel: 'UGEL Sur' },
      { idUgel: 2, nombreDeLaUgel: 'UGEL Norte' }
    ];

    service.getAll$().subscribe((data) => {
      expect(data.length).toBe(2);
      expect(data).toEqual(dummyUgeles);
    });

    const req = httpMock.expectOne(resources.ugeles.ugel);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUgeles);
  });

  it('should fetch UGEL by id', () => {
    const dummyUgel: UgelModel = { idUgel: 1, nombreDeLaUgel: 'UGEL Central' };

    service.getById$(1).subscribe((data) => {
      expect(data).toEqual(dummyUgel);
    });

    const req = httpMock.expectOne(`${resources.ugeles.ugel}/1`);
    expect(req.request.method).toBe('GET');
    req.flush(dummyUgel);
  });

  it('should create a UGEL', () => {
    const newUgel: UgelModel = { nombreDeLaUgel: 'UGEL Nueva' };

    service.create$(newUgel).subscribe((data) => {
      expect(data).toEqual(newUgel);
    });

    const req = httpMock.expectOne(resources.ugeles.ugel);
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newUgel);
    req.flush(newUgel);
  });

  it('should update a UGEL', () => {
    const updatedUgel: UgelModel = { idUgel: 1, nombreDeLaUgel: 'UGEL Modificada' };

    service.update$(1, updatedUgel).subscribe((data) => {
      expect(data).toEqual(updatedUgel);
    });

    const req = httpMock.expectOne(`${resources.ugeles.ugel}/1`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updatedUgel);
    req.flush(updatedUgel);
  });

  it('should delete a UGEL', () => {
    service.delete$(1).subscribe((data) => {
      expect(data).toBeUndefined();
    });

    const req = httpMock.expectOne(`${resources.ugeles.ugel}/1`);
    expect(req.request.method).toBe('DELETE');
    req.flush(null);
  });
});
