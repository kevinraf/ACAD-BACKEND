import {SedeModel} from './sede-model';
import {UgelModel} from './ugel-model';

export class InstitucionModel {
  id?: number;
  nombre?: string;
  direccion?: string;

  sedeId?: number;
  sede?: SedeModel;

  ugelId?: number;
  ugel?: UgelModel;
}
