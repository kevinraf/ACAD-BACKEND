import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import {DocenteService} from '../../../core/service/Docente/docente.service';



@Component({
  selector: 'app-new-docente',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-docente.html',
  styleUrls: ['./new-docente.scss']
})
export class NewDocenteComponent {
  docenteForm: FormGroup;

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(
    private fb: FormBuilder,
    private docenteService: DocenteService,
    private router: Router
  ) {
    this.docenteForm = this.fb.group({
      nombreDocente: ['', Validators.required],
      apellidoPaternoDocente: ['', Validators.required],
      apellidoMaternoDocente: ['', Validators.required],
      correoDocente: ['', [Validators.required, Validators.email]],
      telefonoDocente: ['', Validators.required],
      estadoDocente: ['', Validators.required],
      cargoDocente: ['', Validators.required],
      dniDocente: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]],
      antiguedadDocente: ['', [Validators.required, Validators.min(0)]]
    });
  }

  guardar(): void {
    if (this.docenteForm.valid) {
      this.docenteService.create$(this.docenteForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
