import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { CursoService } from '../../../core/service/Curso/curso.service';

@Component({
  selector: 'app-edit-curso',
  standalone: true,
  templateUrl: './edit-curso.component.html',
  styleUrls: ['./edit-curso.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditCursoComponent implements OnInit {
  cursoForm: FormGroup;
  idCurso: number = 0;

  constructor(
    private route: ActivatedRoute,
    private cursoService: CursoService,
    private router: Router
  ) {
    this.cursoForm = new FormGroup({
      nombreCurso: new FormControl('', Validators.required),
      descripcionCurso: new FormControl('', Validators.required),
      horasSemanalesCurso: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idCurso = +params['id'];
      this.cursoService.getById$(this.idCurso).subscribe(data => {
        this.cursoForm.patchValue(data);
      });
    });
  }

  guardar(): void {
    if (this.cursoForm.valid) {
      this.cursoService.update$(this.idCurso, this.cursoForm.value).subscribe(() => {
        alert('Curso actualizado correctamente.');
        this.router.navigate(['/cursos']);


      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/cursos']);

  }
}
