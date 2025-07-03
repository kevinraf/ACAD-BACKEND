import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { SedeService } from '../../../core/service/Sede/sede.service';
import { SedeModel } from '../../models/sede-model';

@Component({
  selector: 'app-edit-sede',
  standalone: true,
  templateUrl: './edit-sede.component.html',
  styleUrls: ['./edit-apoderado.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditSedeComponent implements OnInit {

  sedeForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private sedeService: SedeService
  ) {}

  ngOnInit(): void {
    this.sedeForm = this.fb.group({
      nombreSede: ['', Validators.required]
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.id) {
      this.sedeService.getById$(this.id).subscribe((sede: SedeModel) => {
        this.sedeForm.patchValue(sede);
      });
    }
  }

  guardar(): void {
    if (this.sedeForm.valid) {
      const data: SedeModel = {
        ...this.sedeForm.value,
        idSede: this.id
      };

      this.sedeService.update$(this.id, data).subscribe(() => {
        alert('Sede actualizada correctamente');
        this.router.navigate(['/sedes']); // Ajusta si usas otra ruta
      });
    } else {
      alert('Formulario inválido. Completa todos los campos.');
    }
  }

  cancelar(): void {
    this.router.navigate(['/sedes']); // Ajusta si usas otra ruta
  }
}
