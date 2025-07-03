import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatriculaModel } from '../models/matricula-model';
import { RequisitoModel } from '../models/requisito-model';
import { MatriculaService } from '../../core/service/matricula/matricula.service';
import { RequisitoService } from '../../core/service/requisito/requisito.service';
import { NewMatriculaComponent } from './new/new-matricula.component';
import { saveAs } from "file-saver";

@Component({
  selector: 'app-matricula',
  standalone: true,
  templateUrl: './matricula.html',
  styleUrl: './matricula.scss',
  imports: [CommonModule, FormsModule, NewMatriculaComponent],
})
export class Matricula implements OnInit {

  matriculas: MatriculaModel[] = [];
  requisitos: RequisitoModel[] = [];
  nuevoMatriculaVisible = false;

  nuevoMatricula: MatriculaModel = {
    planAcademico: '',
    institucion: '',
    usuario: '',
    codigoMatricula: '',
    nivel: '',
    idRequisitos: undefined,
  };

  constructor(
    private matriculaService: MatriculaService,
    private requisitoService: RequisitoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarMatriculas();
    this.cargarRequisitos();
  }

  cargarMatriculas(): void {
    this.matriculaService.getMatriculas().subscribe(data => {
      this.matriculas = data;
    });
  }

  cargarRequisitos(): void {
    this.requisitoService.getRequisitos().subscribe(data => {
      this.requisitos = data;
    });
  }

  onNuevoMatriculaGuardado(): void {
    this.nuevoMatriculaVisible = false;
    this.cargarMatriculas();
  }

  mostrarFormularioNuevo(): void {
    this.nuevoMatriculaVisible = !this.nuevoMatriculaVisible;
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/matriculas/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar esta matrícula?')) {
      this.matriculaService.delete$(id).subscribe(() => {
        this.matriculas = this.matriculas.filter(m => m.idMatricula !== id);
      });
    }
  }

  // ✅ Método para exportar a PDF
  descargarPdf(): void {
    this.matriculaService.exportarPdf().subscribe(blob => {
      const file = new Blob([blob], { type: 'application/pdf' });
      saveAs(file, 'matriculas.pdf');
    });
  }
}
