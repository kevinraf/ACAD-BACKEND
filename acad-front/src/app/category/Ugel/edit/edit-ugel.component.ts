import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UgelService } from '../../../core/service/Ugel/ugel.service';
import { UgelModel } from '../../models/ugel-model';

@Component({
  selector: 'app-edit-ugel',
  standalone: true,
  templateUrl: './edit-ugel.component.html',
  styleUrls: ['./edit-ugel.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditUgelComponent implements OnInit {
  ugelForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private ugelService: UgelService
  ) {}

  ngOnInit(): void {
    this.ugelForm = this.fb.group({
      nombreDeLaUgel: ['', Validators.required]
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.id) {
      this.ugelService.getById$(this.id).subscribe((ugel: UgelModel) => {
        this.ugelForm.patchValue(ugel);
      });
    }
  }

  guardar(): void {
    if (this.ugelForm.valid) {
      const ugelData: UgelModel = {
        idUgel: this.id,
        ...this.ugelForm.value
      };

      this.ugelService.update$(this.id, ugelData).subscribe(() => {
        alert('UGEL actualizada correctamente');
        this.router.navigate(['/ugeles']); // Cambia si tu ruta es diferente
      });
    } else {
      alert('Formulario inválido. Verifique los campos.');
    }
  }

  cancelar(): void {
    this.router.navigate(['/ugeles']);
  }
}
