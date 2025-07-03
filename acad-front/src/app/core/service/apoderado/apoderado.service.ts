import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { resources } from '../../resources/resources';
import {ApoderadoModel} from '../../../category/models/apoderado-model';


@Injectable({ providedIn: 'root' })
export class ApoderadoService {

  constructor(private http: HttpClient) {}

  getApoderados(): Observable<ApoderadoModel[]> {
    return this.http.get<ApoderadoModel[]>(resources.apoderados.apoderado);
  }

  getById$(id: number): Observable<ApoderadoModel> {
    return this.http.get<ApoderadoModel>(`${resources.apoderados.apoderado}/${id}`);
  }

  create$(data: ApoderadoModel): Observable<ApoderadoModel> {
    return this.http.post<ApoderadoModel>(resources.apoderados.apoderado, data);
  }

  update$(id: number, data: ApoderadoModel): Observable<ApoderadoModel> {
    return this.http.put<ApoderadoModel>(`${resources.apoderados.apoderado}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.apoderados.apoderado}/${id}`);
  }

}
