import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { InstitucionService } from '../../../core/service/institucion/institucion.service';
import { InstitucionModel } from '../../models/institucion-model';

@Component({
  selector: 'app-edit-institucion',
  templateUrl: './edit-institucion.component.html',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  styleUrls: ['./edit-institucion.componet.scss']
})
export class EditInstitucionComponent implements OnInit {

  institucionForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private institucionService: InstitucionService
  ) {}

  ngOnInit(): void {
    this.institucionForm = this.fb.group({
      nombre: ['', Validators.required],
      direccion: ['', Validators.required],
      ugelId: ['', Validators.required],
      sedeId: ['', Validators.required]
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.id) {
      this.institucionService.getById$(this.id).subscribe((institucion: InstitucionModel) => {
        this.institucionForm.patchValue(institucion);
      });
    }
  }

  guardar(): void {
    if (this.institucionForm.valid) {
      const data: InstitucionModel = {
        ...this.institucionForm.value,
        idInstitution: this.id
      };

      this.institucionService.update$(this.id, data).subscribe(() => {
        alert('Institución actualizada correctamente');
        this.router.navigate(['/institucion']); // Ajusta esta ruta si usas otra
      });
    } else {
      alert('Formulario inválido. Completa todos los campos requeridos.');
    }
  }

  cancelar(): void {
    this.router.navigate(['/institucion']); // Ajusta según tu ruteo
  }
}
