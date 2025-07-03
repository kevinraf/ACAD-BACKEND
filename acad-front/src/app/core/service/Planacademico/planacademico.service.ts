import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import {PlanacademicoModel} from '../../../category/models/planacademico-model';

@Injectable({
  providedIn: 'root',
})
export class PlanacademicoService {
  private api = 'http://localhost:8085/planAcademicos';

  constructor(private http: HttpClient) {}

  // Obtener todos los planes académicos
  getAll$(): Observable<PlanacademicoModel[]> {
    return this.http.get<PlanacademicoModel[]>(this.api);
  }

  // Obtener plan por ID
  getById$(id: number): Observable<PlanacademicoModel> {
    return this.http.get<PlanacademicoModel>(`${this.api}/${id}`);
  }

  // Crear nuevo plan
  create$(data: PlanacademicoModel): Observable<PlanacademicoModel> {
    return this.http.post<PlanacademicoModel>(this.api, data);
  }

  // Actualizar plan
  update$(id: number, data: PlanacademicoModel): Observable<PlanacademicoModel> {
    return this.http.put<PlanacademicoModel>(`${this.api}/${id}`, data);
  }

  // Eliminar plan
  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
