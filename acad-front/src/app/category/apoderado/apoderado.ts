import { Component, OnInit } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ApoderadoService } from '../../core/service/apoderado/apoderado.service';
import { ApoderadoModel } from '../models/apoderado-model';
import {NewApoderadoComponent} from './new/new-apoderado.component';

@Component({
  selector: 'app-apoderado',
  standalone: true,
  templateUrl: './apoderado.html',
  styleUrl: './apoderado.scss',
  imports: [CommonModule,FormsModule,NewApoderadoComponent],
})
export class Apoderado implements OnInit {



  apoderados: ApoderadoModel[] = [];
  nuevoApoderadoVisible = false;


  constructor(
    private apoderadoService: ApoderadoService,
    private router: Router
  ) {}



  nuevoApoderado: ApoderadoModel = {
    nombres: '',
    apellidoPaterno: '',
    apellidoMaterno: '',
    fechaNacimiento: '',
    direccion: '',
    rolFamilia: ''
  };

  mostrarFormularioNuevo(): void {
    this.nuevoApoderadoVisible = !this.nuevoApoderadoVisible;
  }

  onNuevoGuardado(): void {
    this.nuevoApoderadoVisible = false;
    this.cargarApoderados();
  }




  ngOnInit(): void {
    this.cargarApoderados();
  }

  cargarApoderados(): void {
    this.apoderadoService.getApoderados().subscribe((data) => {
      this.apoderados = data;
    });
  }

  editar(id?: number): void {
    if (id != null) {
      this.router.navigate(['/apoderado/editar', id]);
    }
  }

  eliminar(id?: number): void {
    if (id == null) return;

    if (confirm('¿Estás seguro de eliminar este apoderado?')) {
      this.apoderadoService.delete$(id).subscribe(() => {
        this.apoderados = this.apoderados.filter(a => a.idApoderado !== id);
      });
    }
  }
}
