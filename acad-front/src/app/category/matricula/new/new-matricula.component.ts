import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RequisitoModel } from '../../models/requisito-model';
import { MatriculaService } from '../../../core/service/matricula/matricula.service';
import { RequisitoService } from '../../../core/service/requisito/requisito.service';

@Component({
  selector: 'app-new-matricula',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-matricula.html',
  styleUrls: ['./new-matricula.scss']
})
export class NewMatriculaComponent {
  matriculaForm: FormGroup;
  requisitos: RequisitoModel[] = [];

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(
    private fb: FormBuilder,
    private matriculaService: MatriculaService,
    private requisitoService: RequisitoService
  ) {
    this.matriculaForm = this.fb.group({
      planAcademico: ['', Validators.required],
      institucion: ['', Validators.required],
      usuario: ['', Validators.required],
      codigoMatricula: ['', Validators.required],
      nivel: ['', Validators.required],
      idRequisitos: [null, Validators.required]
    });

    this.cargarRequisitos();
  }

  cargarRequisitos(): void {
    this.requisitoService.getRequisitos().subscribe(data => {
      this.requisitos = data;
    });
  }

  guardar(): void {
    if (this.matriculaForm.valid) {
      this.matriculaService.save$(this.matriculaForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
