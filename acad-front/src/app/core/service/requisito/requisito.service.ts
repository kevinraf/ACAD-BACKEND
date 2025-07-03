import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { resources } from '../../resources/resources';
import {RequisitoModel} from '../../../category/models/requisito-model';

@Injectable({ providedIn: 'root' })
export class RequisitoService {

  constructor(private http: HttpClient) {}

  getRequisitos(): Observable<RequisitoModel[]> {
    return this.http.get<RequisitoModel[]>(resources.requisitos.requisito);
  }

  getById$(id: number): Observable<RequisitoModel> {
    return this.http.get<RequisitoModel>(`${resources.requisitos.requisito}/${id}`);
  }

  save$(data: RequisitoModel): Observable<RequisitoModel> {
    return this.http.post<RequisitoModel>(resources.requisitos.requisito, data);
  }

  update$(id: number, data: RequisitoModel): Observable<RequisitoModel> {
    return this.http.put<RequisitoModel>(`${resources.requisitos.requisito}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.requisitos.requisito}/${id}`);
  }

}
