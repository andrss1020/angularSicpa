import { Status } from './status.model';

export interface IDepartments {
  id?: number;
  status?: Status ;
  description?: string;
  name?: string;
  phone?: string;
  enterprisesId?: number;
}

export class Departments implements IDepartments {
  constructor(
    public id?: number,
    public status?: Status,
    public description?: string,
    public name?: string,
    public phone?: string,
    public enterprisesId?: number,
  ) {}
}

// export function getEnterprisesIdentifier(enterprises: IDepartments): number | undefined {
//   return enterprises.id!;
// }
