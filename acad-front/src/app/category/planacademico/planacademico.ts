import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

import { PlanacademicoModel } from '../models/planacademico-model';
import { PlanacademicoService } from '../../core/service/Planacademico/planacademico.service';
import { NewPlanacademicoComponent } from './new/new-planacademico.component';

@Component({
  selector: 'app-planacademico',
  standalone: true,
  templateUrl: './planacademico.html',
  styleUrls: ['./planacademico.scss'],
  imports: [CommonModule, NewPlanacademicoComponent],
})
export class Planacademico implements OnInit {
  planes: PlanacademicoModel[] = []; // lista de planes cargados desde el backend
  nuevoVisible = false; // controla visibilidad del formulario

  constructor(
    private planService: PlanacademicoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarPlanes();
  }

  // Carga todos los planes académicos del backend
  cargarPlanes(): void {
    this.planService.getAll$().subscribe(data => {
      this.planes = data;
    });
  }

  // Muestra u oculta el formulario de creación
  mostrarFormularioNuevo(): void {
    this.nuevoVisible = !this.nuevoVisible;
  }

  // Lógica luego de guardar un nuevo plan
  onNuevoGuardado(): void {
    this.nuevoVisible = false;
    this.cargarPlanes();
  }

  // Navega al formulario de edición
  editar(id?: number): void {
    if (id) this.router.navigate(['/planacademico/editar', id]);
  }

  // Elimina un plan académico con confirmación
  eliminar(id?: number): void {
    if (id && confirm('¿Eliminar plan académico?')) {
      this.planService.delete$(id).subscribe(() => {
        this.planes = this.planes.filter(p => p.idPlanacademico !== id);
      });
    }
  }
}
