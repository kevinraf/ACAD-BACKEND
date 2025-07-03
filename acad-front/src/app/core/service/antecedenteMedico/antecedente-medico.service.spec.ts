import { TestBed } from '@angular/core/testing';

import { AntecedenteMedicoService } from './antecedente-medico.service';

describe('AntecedenteMedicoService', () => {
  let service: AntecedenteMedicoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AntecedenteMedicoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
