import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { CursoService } from '../../core/service/Curso/curso.service';
import { CursoModel } from '../models/curso-model';
import { NewCursoComponent } from './new/new-curso.component';

@Component({
  selector: 'app-curso',
  standalone: true,
  templateUrl: './curso.html',
  styleUrl: './curso.scss',
  imports: [CommonModule, NewCursoComponent],
})
export class Curso implements OnInit {
  cursos: CursoModel[] = [];
  nuevoCursoVisible = false;

  constructor(private cursoService: CursoService, private router: Router) {}

  ngOnInit(): void {
    this.cargarCursos();
  }

  cargarCursos(): void {
    this.cursoService.getCursos().subscribe(data => {
      this.cursos = data;
    });
  }

  mostrarFormularioNuevo(): void {
    this.nuevoCursoVisible = !this.nuevoCursoVisible;
  }

  onNuevoGuardado(): void {
    this.nuevoCursoVisible = false;
    this.cargarCursos();
  }

  editar(id?: number): void {
    if (id) this.router.navigate(['/cursos/editar', id]);
  }


  eliminar(id?: number): void {
    if (id && confirm('¿Eliminar curso?')) {
      this.cursoService.delete$(id).subscribe(() => {
        this.cursos = this.cursos.filter(c => c.idCurso !== id);
      });
    }
  }
}
