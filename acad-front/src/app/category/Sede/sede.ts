import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { NewSedeComponent } from './new/new-sede.component';
import { SedeModel } from '../models/sede-model';
import { SedeService } from '../../core/service/Sede/sede.service';

@Component({
  selector: 'app-sede',
  standalone: true,
  templateUrl: './sede.html',
  styleUrl: './sede.scss',
  imports: [CommonModule, FormsModule, NewSedeComponent]
})
export class Sede implements OnInit {

  sedes: SedeModel[] = [];
  nuevaSedeVisible = false;

  constructor(
    private sedeService: SedeService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarSedes();
  }

  cargarSedes(): void {
    this.sedeService.getSedes().subscribe((data) => {
      this.sedes = data;
    });

  }

  mostrarFormularioNuevo(): void {
    this.nuevaSedeVisible = !this.nuevaSedeVisible;
  }

  onNuevoGuardado(): void {
    this.nuevaSedeVisible = false;
    this.cargarSedes();
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/sedes/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar esta sede?')) {
      this.sedeService.delete$(id).subscribe(() => {
        this.sedes = this.sedes.filter(s => s.idSede !== id);
      });
    }
  }
}
