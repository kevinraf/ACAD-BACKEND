import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { PlanacademicoService } from '../../../core/service/Planacademico/planacademico.service';

@Component({
  selector: 'app-edit-planacademico',
  standalone: true,
  templateUrl: './edit-planacademico.component.html',
  styleUrls: ['./edit-planacademico.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditPlanacademicoComponent implements OnInit {
  planForm: FormGroup;
  idPlanacademico: number = 0;

  constructor(
    private route: ActivatedRoute,
    private planService: PlanacademicoService,
    private router: Router
  ) {
    this.planForm = new FormGroup({
      nombrePlanacademico: new FormControl('', Validators.required),
      descripcionPlanacademico: new FormControl('', Validators.required),
      estadoPlanacademico: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idPlanacademico = +params['id'];
      this.planService.getById$(this.idPlanacademico).subscribe(data => {
        this.planForm.patchValue(data);
      });
    });
  }

  guardar(): void {
    if (this.planForm.valid) {
      this.planService.update$(this.idPlanacademico, this.planForm.value).subscribe(() => {
        alert('Plan académico actualizado correctamente.');
        this.router.navigate(['/planacademico']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/planacademico']);
  }
}
