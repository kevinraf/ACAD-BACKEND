import { ApoderadoModel } from './apoderado-model';
import { AntecedenteMedicoModel } from './antedecedente-medico-model';

export class RequisitoModel {
  idRequisitos?: number;
  dniEstudiante?: string;
  partidaNacimientoOriginal?: string;
  resolucionRectoralDeTraslado?: string;
  certificadoDeEstudio?: string;
  fichaMatriculaGeneradoPorSIAGE?: string;
  constanciaDeComportamiento?: string;
  constanciaDeNoAdeudo?: string;
  familiarMilitar?: string;

  idApoderado?: number;
  apoderado?: ApoderadoModel;

  idAntecedenteMedico?: number;
  antecedenteMedico?: AntecedenteMedicoModel;
}
