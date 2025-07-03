import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { resources } from '../../resources/resources';

import {AntecedenteMedicoModel} from '../../../category/models/antedecedente-medico-model';


@Injectable({ providedIn: 'root' })
export class AntecedenteMedicoService {

  constructor(private http: HttpClient) {}

  getAntecedenteMedico(): Observable<AntecedenteMedicoModel[]> {
    return this.http.get<AntecedenteMedicoModel[]>(resources.antecedenteMedicos.antecedenteMedico);
  }

  getById$(id: number): Observable<AntecedenteMedicoModel> {
    return this.http.get<AntecedenteMedicoModel>(`${resources.antecedenteMedicos.antecedenteMedico}/${id}`);
  }

  create$(data: AntecedenteMedicoModel): Observable<AntecedenteMedicoModel> {
    return this.http.post<AntecedenteMedicoModel>(resources.antecedenteMedicos.antecedenteMedico, data);
  }

  update$(id: number, data: AntecedenteMedicoModel): Observable<AntecedenteMedicoModel> {
    return this.http.put<AntecedenteMedicoModel>(`${resources.antecedenteMedicos.antecedenteMedico}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.antecedenteMedicos.antecedenteMedico}/${id}`);
  }

}
