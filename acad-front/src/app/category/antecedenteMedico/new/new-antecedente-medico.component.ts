import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApoderadoService } from '../../../core/service/apoderado/apoderado.service';
import { Output, EventEmitter } from '@angular/core';
import {AntecedenteMedicoService} from '../../../core/service/antecedenteMedico/antecedente-medico.service';
@Component({
  selector: 'app-new-antecedente-medico',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-antecedente-medico.html',
  styleUrls: ['./new-antecedente-medico.scss']
})
export class NewAntecedenteMedicoComponent {
  antedecenteMedicoForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private antecedenteMedicoService: AntecedenteMedicoService,
    private router: Router
  ) {
    this.antedecenteMedicoForm = this.fb.group({
      edad: ['', Validators.required],
      altura: ['', Validators.required],
      peso: ['', Validators.required],
      grupoSanguineo: ['', Validators.required],
      enfermedadesPadecidas: ['', Validators.required],
      antecedenteDeHospitalizacionoDeCirugias: ['', Validators.required],
      seEncuentraMedicado: ['', Validators.required],
      presentaDiscapacidad: ['', Validators.required],
      alergias: ['', Validators.required],
      estadoPsicologico: ['', Validators.required],
    });
  }
  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  guardar() {
    if (this.antedecenteMedicoForm.valid) {
      this.antecedenteMedicoService.create$(this.antedecenteMedicoForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar() {
    this.onCancelar.emit();
  }
}
