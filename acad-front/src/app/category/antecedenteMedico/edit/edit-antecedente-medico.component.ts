import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { ApoderadoService } from '../../../core/service/apoderado/apoderado.service';
import { ApoderadoModel } from '../../models/apoderado-model';
import {AntecedenteMedicoService} from '../../../core/service/antecedenteMedico/antecedente-medico.service';

@Component({
  selector: 'app-edit-apoderado',
  standalone: true,
  templateUrl: './edit-antecedente-medico.component.html',
  styleUrls: ['./edit-antecedente-medico.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditAntecedenteMedicoComponent implements OnInit {
  antecedenteMedicoForm: FormGroup;
  idAntecedenteMedico: number = 0;

  constructor(
    private route: ActivatedRoute,
    private antecedenteMedicoService: AntecedenteMedicoService,
    private router: Router
  ) {
    this.antecedenteMedicoForm = new FormGroup({
      edad: new FormControl('', Validators.required),
      altura: new FormControl('', Validators.required),
      peso: new FormControl('', Validators.required),
      grupoSanguineo: new FormControl('', Validators.required),
      enfermedadesPadecidas: new FormControl('', Validators.required),
      antecedenteDeHospitalizacionoDeCirugias: new FormControl('', Validators.required),
      seEncuentraMedicado: new FormControl('', Validators.required),
      presentaDiscapacidad: new FormControl('', Validators.required),
      alergias: new FormControl('', Validators.required),
      estadoPsicologico: new FormControl('', Validators.required),
    });

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idAntecedenteMedico = +params['id'];
      this.antecedenteMedicoService.getById$(this.idAntecedenteMedico).subscribe(data => {
        this.antecedenteMedicoForm.patchValue(data);
      });
    });
  }

  guardar(): void {
    if (this.antecedenteMedicoForm.valid) {
      this.antecedenteMedicoService.update$(this.idAntecedenteMedico, this.antecedenteMedicoForm.value).subscribe(() => {
        alert('AntecedenteMedico actualizado correctamente.');
        this.router.navigate(['/antecedentes-medicos']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/antecedentes-medicos']);
  }
}
