import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8085/auth'; // ✅ Ajusta si usas otra ruta base

  constructor(private http: HttpClient) {}

  // Método para iniciar sesión
  login(userName: string, password: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, { userName, password });
  }

  // Guardar el token en localStorage
  saveToken(token: string): void {
    localStorage.setItem('token', token);
  }

  // Obtener el token guardado
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Verifica si hay token
  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  // Elimina el token del almacenamiento
  logout(): void {
    localStorage.removeItem('token');
  }


  register(userName: string, password: string, email: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, { userName, password, email });
  }

}
