import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import {InstitucionModel} from '../models/institucion-model';
import {SedeModel} from '../models/sede-model';
import {UgelModel} from '../models/ugel-model';
import {InstitucionService} from '../../core/service/institucion/institucion.service';
import {SedeService} from '../../core/service/Sede/sede.service';
import {UgelService} from '../../core/service/Ugel/ugel.service';
import {NewInstitucionComponent} from './new/new-institucion.component';


@Component({
  selector: 'app-institucion',
  standalone: true,
  templateUrl: './institucion.html',
  styleUrl: './institucion.scss',
  imports: [CommonModule, FormsModule, NewInstitucionComponent],
})
export class InstitucionComponent implements OnInit {

  institucion: InstitucionModel[] = [];
  sedes: SedeModel[] = [];
  ugeles: UgelModel[] = [];

  nuevaInstitucionVisible = false;

  nuevaInstitucion: InstitucionModel = {
    nombre: '',
    direccion: '',
    sedeId: undefined,
    ugelId: undefined
  };


  constructor(
    private institucionService: InstitucionService,
    private sedeService: SedeService,
    private ugelService: UgelService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.cargarInstituciones();
    this.cargarSedes();
    this.cargarUgeles();
  }

  cargarInstituciones(): void {
    this.institucionService.getAll$().subscribe(data => {
      this.institucion = data;
    });
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

  onNuevaInstitucionGuardada(): void {
    this.nuevaInstitucionVisible = false;
    this.cargarInstituciones();
  }

  mostrarFormularioNuevo(): void {
    this.nuevaInstitucionVisible = !this.nuevaInstitucionVisible;
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/institucion/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar esta institución?')) {
      this.institucionService.delete$(id).subscribe(() => {
        this.institucion = this.institucion.filter(i => i.id !== id);
      });
    }
  }
}
