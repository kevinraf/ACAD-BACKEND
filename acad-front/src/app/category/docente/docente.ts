import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { DocenteService } from '../../core/service/Docente/docente.service';
import { DocenteModel } from '../models/docente-model';
import { NewDocenteComponent } from './new/new-docente.component';

@Component({
  selector: 'app-docente',
  standalone: true,
  templateUrl: './docente.html',
  styleUrls: ['./docente.scss'],
  imports: [CommonModule, FormsModule, NewDocenteComponent],
})
export class Docente implements OnInit {
  filtroDni: string = '';
  filtroCargo: string = '';

  docentes: DocenteModel[] = [];
  nuevoDocenteVisible = false;

  constructor(private docenteService: DocenteService, private router: Router) {}

  ngOnInit(): void {
    this.cargarDocentes();
  }

  cargarDocentes(): void {
    this.docenteService.getDocentes().subscribe(data => {
      this.docentes = data;
    });
  }

  mostrarFormularioNuevo(): void {
    this.nuevoDocenteVisible = !this.nuevoDocenteVisible;
  }

  onNuevoGuardado(): void {
    this.nuevoDocenteVisible = false;
    this.cargarDocentes();
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/docentes/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id != null && confirm('¿Estás seguro de eliminar este docente?')) {
      this.docenteService.delete$(id).subscribe(() => {
        this.docentes = this.docentes.filter(d => d.idDocente !== id);
      });
    }
  }

  buscarPorDni(): void {
    if (!this.filtroDni.trim()) return;
    this.docenteService.buscarPorDni$(this.filtroDni).subscribe(data => {
      this.docentes = data;
    });
  }

  buscarPorCargo(): void {
    if (!this.filtroCargo.trim()) return;
    this.docenteService.buscarPorCargo$(this.filtroCargo).subscribe(data => {
      this.docentes = data;
    });
  }

  buscarAntiguedadMenorA5(): void {
    this.docenteService.buscarAntiguedadMenorA5$().subscribe(data => {
      this.docentes = data;
    });
  }

  buscarAntiguedadMayorA5(): void {
    this.docenteService.buscarAntiguedadMayorA5$().subscribe(data => {
      this.docentes = data;
    });
  }
}
