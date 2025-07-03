import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApoderadoService } from '../../../core/service/apoderado/apoderado.service';
import { Output, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-new-apoderado',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-apoderado.html',
  styleUrls: ['./new-apoderado.scss']
})
export class NewApoderadoComponent {
  apoderadoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private apoderadoService: ApoderadoService,
    private router: Router
  ) {
    this.apoderadoForm = this.fb.group({
      nombres: ['', Validators.required],
      apellidoPaterno: ['', Validators.required],
      apellidoMaterno: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      direccion: ['', Validators.required],
      rolFamilia: ['', Validators.required]
    });
  }
  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  guardar() {
    if (this.apoderadoForm.valid) {
      this.apoderadoService.create$(this.apoderadoForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar() {
    this.onCancelar.emit();
  }
}
