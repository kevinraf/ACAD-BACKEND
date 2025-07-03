import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { PlanacademicoService } from '../../../core/service/Planacademico/planacademico.service';

@Component({
  selector: 'app-new-planacademico',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './new-planacademico.html',
  styleUrls: ['./new-planacademico.scss']
})
export class NewPlanacademicoComponent {
  planForm: FormGroup;

  @Output() onGuardar = new EventEmitter<void>();
  @Output() onCancelar = new EventEmitter<void>();

  constructor(private fb: FormBuilder, private planService: PlanacademicoService) {
    this.planForm = this.fb.group({
      nombrePlanacademico: ['', Validators.required],
      descripcionPlanacademico: ['', Validators.required],
      estadoPlanacademico: ['', Validators.required]
    });
  }

  guardar(): void {
    if (this.planForm.valid) {
      this.planService.create$(this.planForm.value).subscribe(() => {
        this.onGuardar.emit();
        this.planForm.reset();
      });
    }
  }

  cancelar(): void {
    this.onCancelar.emit();
  }
}
