import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DocenteModel } from '../../../category/models/docente-model';
import { resources } from '../../resources/resources';

@Injectable({ providedIn: 'root' })
export class DocenteService {

  constructor(private http: HttpClient) {}

  getDocentes(): Observable<DocenteModel[]> {
    return this.http.get<DocenteModel[]>(resources.docentes.docente);
  }

  getById$(id: number): Observable<DocenteModel> {
    return this.http.get<DocenteModel>(`${resources.docentes.docente}/${id}`);
  }

  create$(data: DocenteModel): Observable<DocenteModel> {
    return this.http.post<DocenteModel>(resources.docentes.docente, data);
  }

  update$(id: number, data: DocenteModel): Observable<DocenteModel> {
    return this.http.put<DocenteModel>(`${resources.docentes.docente}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.docentes.docente}/${id}`);
  }

  buscarPorDni$(dni: string): Observable<DocenteModel[]> {
    return this.http.get<DocenteModel[]>(`${resources.docentes.docente}/dni/${dni}`);
  }

  buscarPorCargo$(cargo: string): Observable<DocenteModel[]> {
    return this.http.get<DocenteModel[]>(`${resources.docentes.docente}/cargo/${cargo}`);
  }

  buscarAntiguedadMenorA5$(): Observable<DocenteModel[]> {
    return this.http.get<DocenteModel[]>(`${resources.docentes.docente}/antiguedad/menor-a-5`);
  }

  buscarAntiguedadMayorA5$(): Observable<DocenteModel[]> {
    return this.http.get<DocenteModel[]>(`${resources.docentes.docente}/antiguedad/mayor-a-5`);
  }
}
