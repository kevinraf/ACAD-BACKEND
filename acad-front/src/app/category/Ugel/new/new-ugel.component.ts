import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { UgelService } from '../../../core/service/Ugel/ugel.service';
import { UgelModel } from '../../models/ugel-model';

@Component({
  selector: 'app-new-ugel',
  standalone: true,
  templateUrl: './new-ugel.component.html',
  styleUrls: ['./new-ugel.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class NewUgelComponent {
  ugelForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private ugelService: UgelService,
    private router: Router
  ) {
    this.ugelForm = this.fb.group({
      nombreDeLaUgel: ['', Validators.required]
    });
  }

  guardar(): void {
    if (this.ugelForm.valid) {
      const nuevaUgel: UgelModel = this.ugelForm.value;
      this.ugelService.create$(nuevaUgel).subscribe(() => {
        alert('UGEL creada correctamente');
        this.router.navigate(['/ugeles']);
      });
    } else {
      alert('Formulario inválido. Verifique los campos.');
    }
  }

  cancelar(): void {
    this.router.navigate(['/ugeles']);
  }
}
