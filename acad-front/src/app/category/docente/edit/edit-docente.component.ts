import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import {DocenteService} from '../../../core/service/Docente/docente.service';


@Component({
  selector: 'app-edit-docente',
  standalone: true,
  templateUrl: './edit-docente.component.html',
  styleUrls: ['./edit-docente.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditDocenteComponent implements OnInit {
  docenteForm: FormGroup;
  idDocente: number = 0;

  constructor(
    private route: ActivatedRoute,
    private docenteService: DocenteService,
    private router: Router
  ) {
    this.docenteForm = new FormGroup({
      nombreDocente: new FormControl('', Validators.required),
      apellidoPaternoDocente: new FormControl('', Validators.required),
      apellidoMaternoDocente: new FormControl('', Validators.required),
      correoDocente: new FormControl('', [Validators.required, Validators.email]),
      telefonoDocente: new FormControl('', Validators.required),
      estadoDocente: new FormControl('', Validators.required),
      cargoDocente: new FormControl('', Validators.required),
      dniDocente: new FormControl('', [Validators.required, Validators.minLength(8), Validators.maxLength(8)]),
      antiguedadDocente: new FormControl('', [Validators.required, Validators.min(0)])
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idDocente = +params['id'];
      this.docenteService.getById$(this.idDocente).subscribe(data => {
        this.docenteForm.patchValue(data);
      });
    });
  }

  guardar(): void {
    if (this.docenteForm.valid) {
      this.docenteService.update$(this.idDocente, this.docenteForm.value).subscribe(() => {
        alert('Docente actualizado correctamente.');
        this.router.navigate(['/docente']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/docente']);
  }


}
