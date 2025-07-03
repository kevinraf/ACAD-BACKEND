import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule } from '@angular/common';

import { ApoderadoService } from '../../../core/service/apoderado/apoderado.service';
import { ApoderadoModel } from '../../models/apoderado-model';

@Component({
  selector: 'app-edit-apoderado',
  standalone: true,
  templateUrl: './edit-apoderado.component.html',
  styleUrls: ['./edit-apoderado.component.scss'],
  imports: [CommonModule, ReactiveFormsModule]
})
export class EditApoderadoComponent implements OnInit {
  apoderadoForm: FormGroup;
  idApoderado: number = 0;

  constructor(
    private route: ActivatedRoute,
    private apoderadoService: ApoderadoService,
    private router: Router
  ) {
    this.apoderadoForm = new FormGroup({
      nombres: new FormControl('', Validators.required),
      apellidoPaterno: new FormControl('', Validators.required),
      apellidoMaterno: new FormControl('', Validators.required),
      fechaNacimiento: new FormControl('', Validators.required),
      direccion: new FormControl('', Validators.required),
      rolFamilia: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idApoderado = +params['id'];
      this.apoderadoService.getById$(this.idApoderado).subscribe(data => {
        this.apoderadoForm.patchValue(data);
      });
    });
  }

  guardar(): void {
    if (this.apoderadoForm.valid) {
      this.apoderadoService.update$(this.idApoderado, this.apoderadoForm.value).subscribe(() => {
        alert('AntecedenteMedico actualizado correctamente.');
        this.router.navigate(['/apoderado']);
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/apoderado']);
  }
}
