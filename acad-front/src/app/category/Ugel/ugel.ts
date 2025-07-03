import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UgelService } from '../../core/service/Ugel/ugel.service';
import { UgelModel } from '../models/ugel-model';
import { FormsModule } from '@angular/forms';
import { NewUgelComponent } from './new/new-ugel.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ugel',
  standalone: true,
  templateUrl: './ugel.html',
  styleUrls: ['./ugel.scss'],
  imports: [CommonModule, FormsModule, NewUgelComponent]
})
export class Ugel implements OnInit {
  ugeles: UgelModel[] = [];
  nuevoUgelVisible = false;

  constructor(private ugelService: UgelService, private router: Router) {}

  ngOnInit(): void {
    this.cargarUgeles();
  }

  cargarUgeles(): void {
    this.ugelService.getAll$().subscribe((data) => {
      this.ugeles = data;
    });
  }

  mostrarFormularioNuevo(): void {
    this.nuevoUgelVisible = !this.nuevoUgelVisible;
  }

  onNuevoGuardado(): void {
    this.nuevoUgelVisible = false;
    this.cargarUgeles();
  }

  editar(id?: number): void {
    if (id) {
      this.router.navigate(['/ugeles/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id && confirm('¿Estás seguro de eliminar esta UGEL?')) {
      this.ugelService.delete$(id).subscribe(() => {
        this.ugeles = this.ugeles.filter(u => u.idUgel !== id);
      });
    }
  }
}
