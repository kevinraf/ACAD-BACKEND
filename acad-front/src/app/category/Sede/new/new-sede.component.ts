import { Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { SedeService } from '../../../core/service/Sede/sede.service';
import { SedeModel } from '../../models/sede-model';

@Component({
  selector: 'app-new-sede',
  standalone: true,
  templateUrl: './new-sede.component.html',
  styleUrls: ['./new-sede.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class NewSedeComponent {
  sedeForm: FormGroup;

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(
    private fb: FormBuilder,
    private sedeService: SedeService
  ) {
    this.sedeForm = this.fb.group({
      nombreSede: ['', Validators.required]
    });
  }

  guardar(): void {
    if (this.sedeForm.valid) {
      const data: SedeModel = this.sedeForm.value;

      this.sedeService.create$(data).subscribe(() => {
        alert('Sede registrada correctamente');
        this.onGuardar.emit();
        this.sedeForm.reset();
      });
    } else {
      alert('Formulario inválido. Complete todos los campos.');
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
