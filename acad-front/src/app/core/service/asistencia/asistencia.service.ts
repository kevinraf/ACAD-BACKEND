import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { resources } from '../../resources/resources';
import { AsistenciaModel } from '../../../category/models/asistencia-model';

@Injectable({ providedIn: 'root' })
export class AsistenciaService {

  constructor(private http: HttpClient) {}

  getAll$(): Observable<AsistenciaModel[]> {
    return this.http.get<AsistenciaModel[]>(resources.asistencias.asistencia);
  }

  getById$(id: number): Observable<AsistenciaModel> {
    return this.http.get<AsistenciaModel>(`${resources.asistencias.asistencia}/${id}`);
  }

  create$(data: AsistenciaModel): Observable<AsistenciaModel> {
    return this.http.post<AsistenciaModel>(resources.asistencias.asistencia, data);
  }

  update$(id: number, data: AsistenciaModel): Observable<AsistenciaModel> {
    return this.http.put<AsistenciaModel>(`${resources.asistencias.asistencia}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.asistencias.asistencia}/${id}`);
  }

  // filtros
  findByUser$(userName: string): Observable<AsistenciaModel[]> {
    console.log('GET', `${resources.asistencias.asistencia}/buscar-usuario`, { params: { userName } });
    return this.http.get<AsistenciaModel[]>(`${resources.asistencias.asistencia}/buscar-usuario`, {
      params: { userName }
    });
  }

  findByCurso$(nombre: string): Observable<AsistenciaModel[]> {
    return this.http.get<AsistenciaModel[]>(`${resources.asistencias.asistencia}/buscar-curso`, {
      params: { nombre }
    });
  }

  findByFecha$(desde: string, hasta: string): Observable<AsistenciaModel[]> {
    return this.http.get<AsistenciaModel[]>(`${resources.asistencias.asistencia}/buscar-fecha`, {
      params: { desde, hasta }
    });
  }

  // estadísticas
  masFaltas$(): Observable<any[]> {
    return this.http.get<any[]>(`${resources.asistencias.asistencia}/faltas`);
  }

  masTardanzas$(): Observable<any[]> {
    return this.http.get<any[]>(`${resources.asistencias.asistencia}/tardanzas`);
  }

  masPresentes$(): Observable<any[]> {
    return this.http.get<any[]>(`${resources.asistencias.asistencia}/presentes`);
  }
  getAsistencias(): Observable<any[]> {
    return this.http.get<any[]>(resources.asistencias.asistencia); // Se agregará la baseUrl por el interceptor
  }
}
