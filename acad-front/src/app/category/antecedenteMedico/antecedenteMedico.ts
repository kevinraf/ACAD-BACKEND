import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

import {NewAntecedenteMedicoComponent} from './new/new-antecedente-medico.component';
import {AntecedenteMedicoModel} from '../models/antedecedente-medico-model';
import {AntecedenteMedicoService} from '../../core/service/antecedenteMedico/antecedente-medico.service';

@Component({
  selector: 'app-apoderado',
  standalone: true,
  templateUrl: './antecedenteMedico.html',
  styleUrl: './antecedenteMedico.scss',
  imports: [CommonModule,FormsModule,NewAntecedenteMedicoComponent],
})
export class AntecedenteMedico implements OnInit {



  antecedentesMedicos: AntecedenteMedicoModel[] = [];
  nuevoAntecedenteMedicoVisible = false;


  constructor(
    private antecedenteMedicoService: AntecedenteMedicoService,
    private router: Router
  ) {}



  nuevoAntecedenteMedico: AntecedenteMedicoModel = {
    edad: '',
    altura: '',
    peso: '',
    grupoSanguineo: '',
    enfermedadesPadecidas: '',
    antecedenteDeHospitalizacionoDeCirugias: '',
    seEncuentraMedicado: '',
    presentaDiscapacidad: '',
    alergias: '',
    estadoPsicologico: ''

  };

  mostrarFormularioNuevo(): void {
    this.nuevoAntecedenteMedicoVisible = !this.nuevoAntecedenteMedicoVisible;
  }

  onNuevoGuardado(): void {
    this.nuevoAntecedenteMedicoVisible = false;
    this.cargarAntecedentesMedicos();
  }




  ngOnInit(): void {
    this.cargarAntecedentesMedicos();
  }

  cargarAntecedentesMedicos(): void {
    this.antecedenteMedicoService.getAntecedenteMedico().subscribe((data) => {
      this.antecedentesMedicos = data;
    });
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/antecedentes-medicos/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar este antecedente Medico?')) {
      this.antecedenteMedicoService.delete$(id).subscribe(() => {
        this.antecedentesMedicos = this.antecedentesMedicos.filter(a => a.idAntecedenteMedico !== id);
      });
    }
  }
}
