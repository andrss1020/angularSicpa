import { environment } from './../../environments/environment';
import { createRequestOption } from './../util/request/request-util';
import { IEnterprises } from '../shared/model/enterprises.model';
import { ApplicationConfigService } from './../util/application-config.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

export type EntityResponseType = HttpResponse<IEnterprises>;
export type EntityArrayResponseType = HttpResponse<IEnterprises[]>;
export type EntityMenuGridResponseType = HttpResponse<IEnterprises>;

@Injectable({ providedIn: 'root' })
export class EnterpriseService {
  //protected resourceUrl = this.applicationConfigService.getEndpointFor('api/enterprises');
  public resourceUrl = environment.baseUrl + 'api/enterprises';
  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  loadPage(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEnterprises[]>(`${this.resourceUrl}/load-page`, {
      params: options,
      observe: 'response',
    });
  }

  getEnterprisesById(id: number): Observable<EntityResponseType> {
    return this.http.get<IEnterprises>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  
  create(enterprises: IEnterprises): Observable<EntityResponseType> {
    return this.http.post<IEnterprises>(this.resourceUrl, enterprises, { observe: 'response' });
  }

  update(enterprises: IEnterprises): Observable<EntityResponseType> {
    return this.http.put<IEnterprises>(`${this.resourceUrl}/`, enterprises, { observe: 'response' });
  }

  delete(id: number): Observable<EntityMenuGridResponseType> {
    return this.http.delete<IEnterprises>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
