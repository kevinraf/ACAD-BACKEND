import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgClass } from '@angular/common';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router'; // 👈 IMPORTANTE

@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  imports: [FormsModule, NgClass] // 👈 AÑADIR NgClass AQUÍ
})
export class RegisterComponent {


passwordVisible = false;


  user = '';
  password = '';
  cargo = '';
  estado = true;

  constructor(private http: HttpClient, private router: Router) {}

  register() {
    const data = {
      userName: this.user,
      password: this.password,
      cargo: this.cargo,
      estado: this.estado
    };

    this.http.post('http://localhost:8085/auth/create', data).subscribe({
      next: () => {
        alert('Usuario registrado correctamente');
        this.router.navigate(['/login']);
      },
      error: () => alert('Error al registrar')
    });
  }
}
