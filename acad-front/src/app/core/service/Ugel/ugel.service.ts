import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UgelModel } from '../../../category/models/ugel-model';
import { resources } from '../../resources/resources';

@Injectable({
  providedIn: 'root'
})
export class UgelService {
  constructor(private http: HttpClient) {}

  getAll$(): Observable<UgelModel[]> {
    return this.http.get<UgelModel[]>(resources.ugeles.ugel);
  }

  getById$(id: number): Observable<UgelModel> {
    return this.http.get<UgelModel>(`${resources.ugeles.ugel}/${id}`);
  }

  create$(data: UgelModel): Observable<UgelModel> {
    return this.http.post<UgelModel>(resources.ugeles.ugel, data);
  }

  update$(id: number, data: UgelModel): Observable<UgelModel> {
    return this.http.put<UgelModel>(`${resources.ugeles.ugel}/${id}`, data);
  }

  delete$(id: number): Observable<void> {
    return this.http.delete<void>(`${resources.ugeles.ugel}/${id}`);
  }
}
