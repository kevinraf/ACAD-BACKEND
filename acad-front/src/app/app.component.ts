import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';

import {ApoderadoService} from './core/service/apoderado/apoderado.service';
import {Apoderado} from './category/apoderado/apoderado';
import {RequisitoService} from './core/service/requisito/requisito.service';
import {AntecedenteMedicoService} from './core/service/antecedenteMedico/antecedente-medico.service';
import {MatriculaService} from './core/service/matricula/matricula.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `<router-outlet></router-outlet>`
})
export class AppComponent {}
