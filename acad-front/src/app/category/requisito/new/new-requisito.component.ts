import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import {ApoderadoModel} from '../../models/apoderado-model';
import {AntecedenteMedicoModel} from '../../models/antedecedente-medico-model';
import {RequisitoService} from '../../../core/service/requisito/requisito.service';
import {ApoderadoService} from '../../../core/service/apoderado/apoderado.service';
import {AntecedenteMedicoService} from '../../../core/service/antecedenteMedico/antecedente-medico.service';

@Component({
  selector: 'app-new-requisito',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-requisito.html',
  styleUrls: ['./new-requisito.scss']
})
export class NewRequisitoComponent {
  requisitoForm: FormGroup;
  apoderados: ApoderadoModel[] = [];
  antecedentesMedicos: AntecedenteMedicoModel[] = [];

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(
    private fb: FormBuilder,
    private requisitoService: RequisitoService,
    private apoderadoService: ApoderadoService,
    private antecedenteMedicoService: AntecedenteMedicoService
  ) {
    this.requisitoForm = this.fb.group({
      dniEstudiante: ['', Validators.required],
      partidaNacimientoOriginal: ['', Validators.required],
      resolucionRectoralDeTraslado: [''],
      certificadoDeEstudio: [''],
      fichaMatriculaGeneradoPorSIAGE: [''],
      constanciaDeComportamiento: [''],
      constanciaDeNoAdeudo: [''],
      familiarMilitar: [''],
      idApoderado: [null, Validators.required],
      idAntecedenteMedico: [null, Validators.required]
    });

    this.cargarApoderados();
    this.cargarAntecedentes();
  }

  cargarApoderados(): void {
    this.apoderadoService.getApoderados().subscribe(data => {
      this.apoderados = data;
    });
  }


  cargarAntecedentes(): void {
    this.antecedenteMedicoService.getAntecedenteMedico().subscribe(data => {
      this.antecedentesMedicos = data;
    });
  }

  guardar(): void {
    if (this.requisitoForm.valid) {
      this.requisitoService.save$(this.requisitoForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
