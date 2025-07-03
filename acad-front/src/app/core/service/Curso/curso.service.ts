import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CursoModel } from '../../../category/models/curso-model';
import { resources } from '../../resources/resources';

@Injectable({ providedIn: 'root' })
export class CursoService {
  constructor(private http: HttpClient) {}

  getCursos(): Observable<CursoModel[]> {
    return this.http.get<CursoModel[]>(resources.cursos.curso);
  }

  getById$(id: number): Observable<CursoModel> {
    return this.http.get<CursoModel>(`${resources.cursos.curso}/${id}`);
  }

  create$(data: CursoModel): Observable<CursoModel> {
    return this.http.post<CursoModel>(resources.cursos.curso, data);
  }

  update$(id: number, data: CursoModel): Observable<CursoModel> {
    return this.http.put<CursoModel>(`${resources.cursos.curso}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.cursos.curso}/${id}`);
  }
}
