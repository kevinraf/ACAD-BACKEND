import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { resources } from '../../resources/resources';
import { InstitucionModel } from '../../../category/models/institucion-model';

@Injectable({ providedIn: 'root' })
export class InstitucionService {
  private api = 'http://localhost:8085/institucion';
  constructor(private http: HttpClient) {}

  // Obtener todos los planes académicos
  getAll$(): Observable<InstitucionModel[]> {
    return this.http.get<InstitucionModel[]>(this.api);
  }

  getById$(id: number): Observable<InstitucionModel> {
    return this.http.get<InstitucionModel>(`${resources.institucion.institucion}/${id}`);
  }

  save$(data: InstitucionModel): Observable<InstitucionModel> {
    return this.http.post<InstitucionModel>(resources.institucion.institucion, data);
  }

  update$(id: number, data: InstitucionModel): Observable<InstitucionModel> {
    return this.http.put<InstitucionModel>(`${resources.institucion.institucion}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.institucion.institucion}/${id}`);
  }
}
