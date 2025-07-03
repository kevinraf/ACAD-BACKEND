import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SedeModel } from '../../../category/models/sede-model';
import { resources } from '../../resources/resources';

@Injectable({
  providedIn: 'root'
})
export class SedeService {
  constructor(private http: HttpClient) {}

  getSedes(): Observable<SedeModel[]> {
    return this.http.get<SedeModel[]>(resources.sedes.sede);
  }

  getById$(id: number): Observable<SedeModel> {
    return this.http.get<SedeModel>(`${resources.sedes.sede}/${id}`);
  }

  create$(data: SedeModel): Observable<SedeModel> {
    return this.http.post<SedeModel>(resources.sedes.sede, data);
  }

  update$(id: number, data: SedeModel): Observable<SedeModel> {
    return this.http.put<SedeModel>(`${resources.sedes.sede}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.sedes.sede}/${id}`);
  }
}
