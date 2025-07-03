import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {ApoderadoModel} from '../../models/apoderado-model';
import {AntecedenteMedicoModel} from '../../models/antedecedente-medico-model';
import {RequisitoService} from '../../../core/service/requisito/requisito.service';
import {ApoderadoService} from '../../../core/service/apoderado/apoderado.service';
import {AntecedenteMedicoService} from '../../../core/service/antecedenteMedico/antecedente-medico.service';

@Component({
  selector: 'app-edit-requisito',
  standalone: true,
  templateUrl: './edit-requisito.component.html',
  styleUrls: ['./edit-requisito.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditRequisitoComponent implements OnInit {
  requisitoForm: FormGroup;
  idRequisitos: number = 0;

  apoderados: ApoderadoModel[] = [];
  antecedentesMedicos: AntecedenteMedicoModel[] = [];

  constructor(
    private route: ActivatedRoute,
    private requisitoService: RequisitoService,
    private apoderadoService: ApoderadoService,
    private antecedenteMedicoService: AntecedenteMedicoService,
    private router: Router
  ) {
    this.requisitoForm = new FormGroup({
      dniEstudiante: new FormControl('', Validators.required),
      partidaNacimientoOriginal: new FormControl('', Validators.required),
      resolucionRectoralDeTraslado: new FormControl(''),
      certificadoDeEstudio: new FormControl(''),
      fichaMatriculaGeneradoPorSIAGE: new FormControl(''),
      constanciaDeComportamiento: new FormControl(''),
      constanciaDeNoAdeudo: new FormControl(''),
      familiarMilitar: new FormControl(''),
      idApoderado: new FormControl(null, Validators.required),
      idAntecedenteMedico: new FormControl(null, Validators.required)
    });
  }

  ngOnInit(): void {
    this.cargarApoderados();
    this.cargarAntecedentesMedicos();

    this.route.params.subscribe(params => {
      this.idRequisitos = +params['id'];
      this.requisitoService.getById$(this.idRequisitos).subscribe(data => {
        this.requisitoForm.patchValue(data);
      });
    });
  }

  cargarApoderados(): void {
    this.apoderadoService.getApoderados().subscribe(data => {
      this.apoderados = data;
    });
  }

  cargarAntecedentesMedicos(): void {
    this.antecedenteMedicoService.getAntecedenteMedico().subscribe(data => {
      this.antecedentesMedicos = data;
    });
  }

  guardar(): void {
    if (this.requisitoForm.valid) {
      this.requisitoService.update$(this.idRequisitos, this.requisitoForm.value).subscribe(() => {
        alert('Matricula actualizado correctamente.');
        this.router.navigate(['/requisitos']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/requisitos']);
  }
}
