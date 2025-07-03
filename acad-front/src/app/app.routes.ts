import { Routes } from '@angular/router';
import { PrincipalComponent } from './category/principal/principal.component';
import { LoginComponent } from './category/login/login.component';
import { authGuard } from './core/resources/auth.guard';
import {RegisterComponent} from './category/auth/register/register.component'; // 👈 IMPORTANTE

export const routes: Routes = [
  // Redirección por defecto a login
  { path: '', redirectTo: 'principal', pathMatch: 'full' },

  // Rutas públicas
  { path: 'principal', component: PrincipalComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // Rutas protegidas bajo dashboard
  {
    path: '',
    loadComponent: () =>
      import('./category/dashboard/dashboard.component').then((m) => m.DashboardComponent),
    canActivate: [authGuard], // 👈 PROTEGEMOS RUTA
    children: [

      {
        path: 'apoderado',
        loadComponent: () =>
          import('./category/apoderado/apoderado').then((m) => m.Apoderado),
      },
      {
        path: 'apoderado/editar/:id',
        loadComponent: () =>
          import('./category/apoderado/edit/edit-apoderado.component')
            .then((m) => m.EditApoderadoComponent),
      },
      {
        path: 'apoderado/nuevo',
        loadComponent: () =>
          import('./category/apoderado/new/new-apoderado.component')
            .then(m => m.NewApoderadoComponent)
      },

      {
        path: 'antecedentes-medicos',
        loadComponent: () =>
          import('./category/antecedenteMedico/antecedenteMedico')
            .then(m => m.AntecedenteMedico),
      },
      {
        path: 'antecedentes-medicos/editar/:id',
        loadComponent: () =>
          import('./category/antecedenteMedico/edit/edit-antecedente-medico.component')
            .then(m => m.EditAntecedenteMedicoComponent),
      },
      {
        path: 'antecedentes-medicos/nuevo',
        loadComponent: () =>
          import('./category/antecedenteMedico/new/new-antecedente-medico.component')
            .then(m => m.NewAntecedenteMedicoComponent),
      },

      {
        path: 'requisitos',
        loadComponent: () =>
          import('./category/requisito/requisito').then(m => m.Requisito),
      },
      {
        path: 'requisitos/editar/:id',
        loadComponent: () =>
          import('./category/requisito/edit/edit-requisito.component')
            .then(m => m.EditRequisitoComponent),
      },
      {
        path: 'requisitos/nuevo',
        loadComponent: () =>
          import('./category/requisito/new/new-requisito.component')
            .then(m => m.NewRequisitoComponent),
      },

      {
        path: 'matriculas',
        loadComponent: () =>
          import('./category/matricula/matricula').then(m => m.Matricula),
      },
      {
        path: 'matriculas/editar/:id',
        loadComponent: () =>
          import('./category/matricula/edit/edit-matricula.component')
            .then(m => m.EditMatriculaComponent),
      },
      {
        path: 'matriculas/nuevo',
        loadComponent: () =>
          import('./category/matricula/new/new-matricula.component')
            .then(m => m.NewMatriculaComponent),
      },

      {
        path: '',
        redirectTo: 'apoderado',
        pathMatch: 'full',
      },

      {
        path: 'docentes',
        loadComponent: () =>
          import('./category/docente/docente').then((m) => m.Docente),
      },
      {
        path: 'docentes/nuevo',
        loadComponent: () =>
          import('./category/docente/new/new-docente.component').then(m => m.NewDocenteComponent),
      },
      {
        path: 'docentes/editar/:id',
        loadComponent: () =>
          import('./category/docente/edit/edit-docente.component').then(m => m.EditDocenteComponent),
      },

      {
        path: 'cursos',
        loadComponent: () =>
          import('./category/curso/curso').then(m => m.Curso),
      },
      {
        path: 'cursos/editar/:id',
        loadComponent: () =>
          import('./category/curso/edit/edit-curso.component').then(m => m.EditCursoComponent),
      },
      {
        path: 'cursos/nuevo',
        loadComponent: () =>
          import('./category/curso/new/new-curso.component').then(m => m.NewCursoComponent),
      },

      {
        path: 'planacademico',
        loadComponent: () =>
          import('./category/planacademico/planacademico').then(m => m.Planacademico),
      },
      {
        path: 'planacademico/nuevo',
        loadComponent: () =>
          import('./category/planacademico/new/new-planacademico.component').then(m => m.NewPlanacademicoComponent),
      },
      {
        path: 'planacademico/editar/:id',
        loadComponent: () =>
          import('./category/planacademico/edit/edit-planacademico.component').then(m => m.EditPlanacademicoComponent),
      },


      // Institución
      {
        path: 'institucion',
        loadComponent: () =>
          import('./category/Institucion/institucion').then(m => m.InstitucionComponent),
      },
      {
        path: 'institucion/editar/:id',
        loadComponent: () =>
          import('./category/Institucion/edit/edit-institucion.componet')
            .then((m) => m.EditInstitucionComponent),
      },
      {
        path: 'institucion/nuevo',
        loadComponent: () =>
          import('./category/Institucion/new/new-institucion.component')
            .then(m => m.NewInstitucionComponent),
      },

      // Sede
      {
        path: 'sedes',
        loadComponent: () =>
          import('./category/Sede/sede').then((m) => m.Sede),
      },
      {
        path: 'sedes/editar/:id',
        loadComponent: () =>
          import('./category/Sede/edit/edit-sede.component')
            .then((m) => m.EditSedeComponent),
      },
      {
        path: 'sedes/nuevo',
        loadComponent: () =>
          import('./category/Sede/new/new-sede.component')
            .then(m => m.NewSedeComponent),
      },

      // UGEL
      {
        path: 'ugeles',
        loadComponent: () =>
          import('./category/Ugel/ugel').then((m) => m.Ugel),
      },
      {
        path: 'ugeles/editar/:id',
        loadComponent: () =>
          import('./category/Ugel/edit/edit-ugel.component')
            .then((m) => m.EditUgelComponent),
      },
      {
        path: 'ugeles/nuevo',
        loadComponent: () =>
          import('./category/Ugel/new/new-ugel.component')
            .then(m => m.NewUgelComponent),
      },

      // Ruta por defecto
      {
        path: '',
        redirectTo: 'ugeles',
        pathMatch: 'full',
      },

      // NAHUEL

      {
        path: 'asistencia',
        loadComponent: () =>
          import('./category/asistencia/asistencia.component').then(m => m.AsistenciaComponent),
      },
      {
        path: '',
        redirectTo: 'asistencia',
        pathMatch: 'full',
      },
    ]
  }
];
