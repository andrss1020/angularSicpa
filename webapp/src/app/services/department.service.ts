import { IDepartments } from './../shared/model/department.model';
import { environment } from './../../environments/environment';
import { createRequestOption } from './../util/request/request-util';
import { ApplicationConfigService } from './../util/application-config.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

export type EntityResponseType = HttpResponse<IDepartments>;
export type EntityArrayResponseType = HttpResponse<IDepartments[]>;
export type EntityMenuGridResponseType = HttpResponse<IDepartments>;

@Injectable({ providedIn: 'root' })
export class DepartmentService {
  //protected resourceUrl = this.applicationConfigService.getEndpointFor('api/enterprises');
  public resourceUrl = environment.baseUrl + 'api/departments';
  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  loadPage(enterpriseId: number, req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDepartments[]>(`${this.resourceUrl}/load-page/${enterpriseId}`, {
      params: options,
      observe: 'response',
    });
  }

  getDepartmentById(id: number): Observable<EntityResponseType> {
    return this.http.get<IDepartments>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  
  create(enterprises: IDepartments): Observable<EntityResponseType> {
    return this.http.post<IDepartments>(this.resourceUrl, enterprises, { observe: 'response' });
  }

  update(enterprises: IDepartments): Observable<EntityResponseType> {
    return this.http.put<IDepartments>(`${this.resourceUrl}/`, enterprises, { observe: 'response' });
  }

  delete(id: number): Observable<EntityMenuGridResponseType> {
    return this.http.delete<IDepartments>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
