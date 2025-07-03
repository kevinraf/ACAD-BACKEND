import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CursoService } from '../../../core/service/Curso/curso.service';

@Component({
  selector: 'app-new-curso',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-curso.html',
  styleUrls: ['./new-curso.scss']
})
export class NewCursoComponent {
  cursoForm: FormGroup;

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(private fb: FormBuilder, private cursoService: CursoService) {
    this.cursoForm = this.fb.group({
      nombreCurso: ['', Validators.required],
      descripcionCurso: ['', Validators.required],
      horasSemanalesCurso: ['', Validators.required]
    });
  }

  guardar(): void {
    if (this.cursoForm.valid) {
      this.cursoService.create$(this.cursoForm.value).subscribe(() => {
        this.onGuardar.emit();
        this.cursoForm.reset();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
