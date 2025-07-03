import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { resources } from '../../resources/resources';
import { MatriculaModel } from '../../../category/models/matricula-model';

@Injectable({ providedIn: 'root' })
export class MatriculaService {

  constructor(private http: HttpClient) {}

  getMatriculas(): Observable<MatriculaModel[]> {
    return this.http.get<MatriculaModel[]>(resources.matriculas.matricula);
  }

  getById$(id: number): Observable<MatriculaModel> {
    return this.http.get<MatriculaModel>(`${resources.matriculas.matricula}/${id}`);
  }

  save$(data: MatriculaModel): Observable<MatriculaModel> {
    return this.http.post<MatriculaModel>(resources.matriculas.matricula, data);
  }

  update$(id: number, data: MatriculaModel): Observable<MatriculaModel> {
    return this.http.put<MatriculaModel>(`${resources.matriculas.matricula}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.matriculas.matricula}/${id}`);
  }

  // ✅ Método para exportar el PDF de matrículas
  exportarPdf(): Observable<Blob> {
    return this.http.get(`${resources.matriculas.matricula}/exportar-pdf`, {
      responseType: 'blob'
    });
  }
}
