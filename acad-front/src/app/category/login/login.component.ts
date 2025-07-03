import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';
import {AuthService} from '../../core/service/Autenticacion/AuthService';

@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  imports: [FormsModule, NgClass]
})
export class LoginComponent {
  user = '';
  clave = '';
  passwordVisible = false;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.user, this.clave).subscribe({
      next: (data) => {
        this.authService.saveToken(data.token);
        this.router.navigate(['/apoderado']); // ✅ Ruta existente protegida
      },
      error: () => {
        alert('Credenciales incorrectas');
      }
    });
  }

  togglePasswordVisibility() {
    this.passwordVisible = !this.passwordVisible;
  }
}
