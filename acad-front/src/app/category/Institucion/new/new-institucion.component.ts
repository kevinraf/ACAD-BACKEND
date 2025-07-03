import { Component, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

import { InstitucionService } from '../../../core/service/institucion/institucion.service';


import { SedeModel } from '../../models/sede-model';
import { UgelModel } from '../../models/ugel-model';
import {SedeService} from '../../../core/service/Sede/sede.service';
import {UgelService} from '../../../core/service/Ugel/ugel.service';

@Component({
  selector: 'app-new-institucion',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-institucion.component.html',
  styleUrls: ['./new-institucion.componet.scss']
})
export class NewInstitucionComponent {
  institucionForm: FormGroup;
  sedes: SedeModel[] = [];
  ugeles: UgelModel[] = [];

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(
    private fb: FormBuilder,
    private institucionService: InstitucionService,
    private sedeService: SedeService,
    private ugelService: UgelService
  ) {
    this.institucionForm = this.fb.group({
      nombre: ['', Validators.required],
      direccion: ['', Validators.required],
      idSede: [null, Validators.required],
      idUgel: [null, Validators.required]
    });

    this.cargarSedes();
    this.cargarUgeles();
  }

  cargarSedes(): void {
    this.sedeService.getSedes().subscribe(data => {
      this.sedes = data;
    });
  }

  cargarUgeles(): void {
    this.ugelService.getAll$().subscribe(data => {
      this.ugeles = data;
    });
  }

  guardar(): void {
    if (this.institucionForm.valid) {
      this.institucionService.save$(this.institucionForm.value).subscribe(() => {
        this.onGuardar.emit();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
