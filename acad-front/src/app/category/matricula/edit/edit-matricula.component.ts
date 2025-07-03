import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { RequisitoModel } from '../../models/requisito-model';
import { MatriculaService } from '../../../core/service/matricula/matricula.service';
import { RequisitoService } from '../../../core/service/requisito/requisito.service';

@Component({
  selector: 'app-edit-matricula',
  standalone: true,
  templateUrl: './edit-matricula.component.html',
  styleUrls: ['./edit-matricula.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditMatriculaComponent implements OnInit {
  matriculaForm: FormGroup;
  idMatricula: number = 0;

  requisitos: RequisitoModel[] = [];

  constructor(
    private route: ActivatedRoute,
    private matriculaService: MatriculaService,
    private requisitoService: RequisitoService,
    private router: Router
  ) {
    this.matriculaForm = new FormGroup({
      planAcademico: new FormControl('', Validators.required),
      institucion: new FormControl('', Validators.required),
      usuario: new FormControl('', Validators.required),
      codigoMatricula: new FormControl('', Validators.required),
      nivel: new FormControl('', Validators.required),
      idRequisitos: new FormControl(null, Validators.required)
    });
  }

  ngOnInit(): void {
    this.cargarRequisitos();

    this.route.params.subscribe(params => {
      this.idMatricula = +params['id'];
      this.matriculaService.getById$(this.idMatricula).subscribe(data => {
        this.matriculaForm.patchValue(data);
      });
    });
  }

  cargarRequisitos(): void {
    this.requisitoService.getRequisitos().subscribe(data => {
      this.requisitos = data;
    });
  }

  guardar(): void {
    if (this.matriculaForm.valid) {
      this.matriculaService.update$(this.idMatricula, this.matriculaForm.value).subscribe(() => {
        alert('Matrícula actualizada correctamente.');
        this.router.navigate(['/matriculas']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/matriculas']);
  }
}
