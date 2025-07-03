import {AsistenciaModel} from '../models/asistencia-model';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AsistenciaService} from '../../core/service/asistencia/asistencia.service';
import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {of} from 'rxjs';

@Component({
  selector: 'app-asistencia',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './asistencia.component.html',
  styleUrls: ['./asistencia.component.scss']
})
export class AsistenciaComponent implements OnInit {
  // Forms
  filtrosForm: FormGroup;
  addForm: FormGroup;
  editForm: FormGroup;

  // Lists
  asistencias: AsistenciaModel[] = [];
  cursos: string[] = [];
  planes: { id: number, nombrePlan: string }[] = [];
  docentes: string[] = [];

  // Stats
  topFaltas: { usuarioNombre: string; cantidad: number }[] = [];
  topTardanzas: { usuarioNombre: string; cantidad: number }[] = [];
  topPresentes: { usuarioNombre: string; cantidad: number }[] = [];

  // View flags
  showList = true;
  showAdd = false;
  @ViewChild('editDialog') editDialog!: ElementRef<HTMLDialogElement>;
  protected currentEditId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private asistenciaService: AsistenciaService
  ) {
    // Initialize forms
    this.filtrosForm = this.fb.group({
      usuario: [''],
      curso: [''],
      desde: [''],
      hasta: ['']
    });

    this.addForm = this.fb.group({
      usuarioNombre: ['', Validators.required],
      fechaRegistroAsistencia: ['', Validators.required],
      estadoAsistencia: ['', Validators.required]
    });

    this.editForm = this.fb.group({
      usuarioNombre: ['', Validators.required],
      fechaRegistroAsistencia: ['', Validators.required],
      estadoAsistencia: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.loadCombos();
    this.loadStats();
  }

  // ----- LIST VIEW & FILTERS -----
  showListView(): void {
    this.showList = true;
    this.showAdd = false;
    this.closeEditModal();
  }

  buscarPorUsuario(): void {
    const nombre = this.filtrosForm.value.usuario?.trim();
    if (nombre) {
      this.asistenciaService.findByUser$(nombre).subscribe(res => {
        this.asistencias = res;
        this.showListView();
      });
    }
  }

  buscarPorCurso(): void {
    const curso = this.filtrosForm.value.curso?.trim();
    if (curso) {
      this.asistenciaService.findByCurso$(curso).subscribe(res => {
        this.asistencias = res;
        this.showListView();
      });
    }
  }



  buscarPorFecha(): void {
    const desde = this.filtrosForm.get('desde')?.value;
    const hasta = this.filtrosForm.get('hasta')?.value;
    if (desde && hasta) {
      this.asistenciaService.findByFecha$(desde, hasta).subscribe({
        next: res => this.asistencias = res,
        error: err => console.error('Error buscando por fecha:', err)
      });
    }
  }

  // ----- ADD VIEW -----
  showAddForm(): void {
    this.showAdd = true;
    this.showList = false;
    this.closeEditModal();
    this.addForm.reset();
  }

  saveNew(): void {
    if (this.addForm.valid) {
      const nueva: Partial<AsistenciaModel> = {
        usuarioNombre: this.addForm.value.usuarioNombre,
        fechaRegistroAsistencia: this.addForm.value.fechaRegistroAsistencia + 'T00:00:00',
        estadoAsistencia: this.addForm.value.estadoAsistencia
      };
      this.asistenciaService.create$(nueva as AsistenciaModel)
        .subscribe(() => {
          this.loadAll();
          this.showListView();
          this.loadStats();
        });
    }
  }

  cancelAdd(): void {
    this.showListView();
  }

  // ----- EDIT VIEW (modal) -----
  openEditModal(a: AsistenciaModel): void {
    this.currentEditId = a.idAsistencia!;

    // Vuelves a crear el formulario pero inicializando cada control con el valor de 'a'
    this.editForm = this.fb.group({
      usuarioIdUsuario:           [a.usuarioIdUsuario, Validators.required],
      usuarioNombre:             [a.usuarioNombre || ''],  // solo para mostrar
      cursoIdCurso:               [a.cursoIdCurso, Validators.required],
      docenteIdDocente:           [a.docenteIdDocente, Validators.required],
      planAcademicoIdPlanAcademico:[a.planAcademicoIdPlanAcademico, Validators.required],
      fechaRegistroAsistencia:    [(a.fechaRegistroAsistencia || '').split('T')[0], Validators.required],
      estadoAsistencia:           [a.estadoAsistencia?.toLowerCase() || '', Validators.required]
    });

    this.editDialog.nativeElement.showModal();
  }


  closeEditModal(): void {
    this.currentEditId = null;
  }

  saveEdit(): void {
    // Aseguramos que haya un ID y que el formulario esté válido
    if (!this.currentEditId || this.editForm.invalid) return;

    const f = this.editForm.value;

    // Montamos el objeto completo que el backend espera
    const updated: AsistenciaModel = {
      idAsistencia:                 this.currentEditId,
      usuarioIdUsuario:             f.usuarioIdUsuario,
      cursoIdCurso:                 f.cursoIdCurso,
      docenteIdDocente:             f.docenteIdDocente,
      planAcademicoIdPlanAcademico: f.planAcademicoIdPlanAcademico,
      fechaRegistroAsistencia:      f.fechaRegistroAsistencia + 'T00:00:00',
      estadoAsistencia:             f.estadoAsistencia
    };

    this.asistenciaService.update$(this.currentEditId, updated)
      .subscribe(() => {
        this.loadAll();
        this.closeEditModal();
        this.loadStats();
      }, err => {
        console.error('Error actualizando asistencia', err);
      });
  }

  // ----- DATA LOADERS -----
  private loadAll(): void {
    this.asistenciaService.getAll$().subscribe(d => this.asistencias = d);
  }

  private loadCombos(): void {
    this.cursos = ['Curso A', 'Curso B', 'Curso C'];
    this.planes = [
      { id: 1, nombrePlan: 'Plan 2025' },
      { id: 2, nombrePlan: 'Plan 2024' }
    ];
    this.docentes = ['Docente X', 'Docente Y'];
  }

  private loadStats(): void {
    this.asistenciaService.masFaltas$().subscribe(d => this.topFaltas = d);
    this.asistenciaService.masTardanzas$().subscribe(d => this.topTardanzas = d);
    this.asistenciaService.masPresentes$().subscribe(d => this.topPresentes = d);
  }

  protected readonly of = of;


}

