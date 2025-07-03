import { Component } from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import { CommonModule } from '@angular/common';
import {AuthService} from '../../core/service/Autenticacion/AuthService';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  imports: [CommonModule, RouterModule]
})
export class DashboardComponent {

  // 👉 variables independientes para cada submenú
  submenuMatriculaAbierto = false;
  submenuCargaAbierto = false;
  submenuInstituciones = false;
  submenuAsistencia = false;

  constructor(private authService: AuthService, private router: Router) {}

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  logoutt() {
    this.authService.logout();
    this.router.navigate(['/principal']);
  }
}
