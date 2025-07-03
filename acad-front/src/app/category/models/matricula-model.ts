 // Ajusta la ruta si es necesario


 import {RequisitoModel} from './requisito-model';

 export class MatriculaModel {
  idMatricula?: number;
  planAcademico?: string;
  institucion?: string;
  usuario?: string;
  codigoMatricula?: string;
  nivel?: string;
  idRequisitos?: number;
  requisitos?: RequisitoModel;
}
