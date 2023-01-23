import { Status } from './status.model';

export interface IEnterprises {
  id?: number;
  status?: Status ;
  address?: string;
  name?: string;
  phone?: string;
}

export class Enterprises implements IEnterprises {
  constructor(
    public id?: number,
    public status?: Status,
    public address?: string,
    public name?: string,
    public phone?: string,
  ) {}
}

// export function getEnterprisesIdentifier(enterprises: IEnterprises): number | undefined {
//   return enterprises.id!;
// }
