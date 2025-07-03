import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {RequisitoModel} from '../models/requisito-model';
import {ApoderadoModel} from '../models/apoderado-model';
import {AntecedenteMedicoModel} from '../models/antedecedente-medico-model';
import {RequisitoService} from '../../core/service/requisito/requisito.service';
import {ApoderadoService} from '../../core/service/apoderado/apoderado.service';
import {AntecedenteMedicoService} from '../../core/service/antecedenteMedico/antecedente-medico.service';
import {NewRequisitoComponent} from './new/new-requisito.component';


@Component({
  selector: 'app-requisito',
  standalone: true,
  templateUrl: './requisito.html',
  styleUrl: './requisito.scss',
  imports: [CommonModule, FormsModule, NewRequisitoComponent],
})
export class Requisito implements OnInit {

  requisitos: RequisitoModel[] = [];
  apoderados: ApoderadoModel[] = [];
  antecedentesMedicos: AntecedenteMedicoModel[] = [];

  nuevoRequisitoVisible = false;

  nuevoRequisito: RequisitoModel = {
    dniEstudiante: '',
    partidaNacimientoOriginal: '',
    resolucionRectoralDeTraslado: '',
    certificadoDeEstudio: '',
    fichaMatriculaGeneradoPorSIAGE: '',
    constanciaDeComportamiento: '',
    constanciaDeNoAdeudo: '',
    familiarMilitar: '',
    idApoderado: undefined,
    idAntecedenteMedico: undefined
  };

  constructor(
    private requisitoService: RequisitoService,
    private apoderadoService: ApoderadoService,
    private antecedenteService: AntecedenteMedicoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarRequisitos();
    this.cargarApoderados();
    this.cargarAntecedentesMedicos();
  }

  cargarRequisitos(): void {
    this.requisitoService.getRequisitos().subscribe(data => {
      this.requisitos = data;
    });
  }

  cargarApoderados(): void {
    this.apoderadoService.getApoderados().subscribe(data => {
      this.apoderados = data;
    });
  }

  cargarAntecedentesMedicos(): void {
    this.antecedenteService.getAntecedenteMedico().subscribe(data => {
      this.antecedentesMedicos = data;
    });
  }

  onNuevoRequisitoGuardado(): void {
    this.nuevoRequisitoVisible = false;
    this.cargarRequisitos();
  }

  mostrarFormularioNuevo(): void {
    this.nuevoRequisitoVisible = !this.nuevoRequisitoVisible;
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/requisitos/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar este requisito?')) {
      this.requisitoService.delete$(id).subscribe(() => {
        this.requisitos = this.requisitos.filter(r => r.idRequisitos !== id);
      });
    }
  }
}
