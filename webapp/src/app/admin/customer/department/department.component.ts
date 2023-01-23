import { MODAL_EXTRA_LARGE_CONSTANT_COMMON } from './../../../shared/constants/modals.constants';
import { IDepartments, Departments } from './../../../shared/model/department.model';
import { DepartmentService } from './../../../services/department.service';
import { Component, OnInit, Input } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { getNumPage } from 'src/app/util/pagination-util';
import { DetailComponent } from './detail/detail.component';
import { REASON } from 'src/app/shared/constants/reason.constants';

@Component({
  selector: 'app-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {
  @Input() enterpriseId!: number;
  page = 0;
  itemsPerPage = 5;
  predicate!: string;
  totalItems = 0;
  ascending!: boolean;
  totalItemsCreated = 0;
  isCreating = false;
  maxSize = 5;
  departmentsList: IDepartments[] | null = null;

  constructor(
    protected departmentService: DepartmentService,
    protected modalService: NgbModal,
    ) { }

  ngOnInit(): void {
    this.loadPage(this.page);
  }

  
  loadPage(page?: number): void {
    const pageToLoad: number = page ?? this.page;
    this.departmentService
      .loadPage(this.enterpriseId,{
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IDepartments[]>) => this.onSuccess(res.body, res.headers));
  }

  edit(id: number): void {
    const createModal = this.modalService.open(DetailComponent, MODAL_EXTRA_LARGE_CONSTANT_COMMON);
    createModal.componentInstance.enterprisesId = id;
    createModal.closed.subscribe(res => res !== REASON.CANCELED && this.loadPage());
  }

  create(): void{
    // const createModal = this.modalService.open(FrMasterBuildDetailsComponent, MODAL_CUSTOM_EXTRA_LARGE_1_CONSTANT_COMMON);
    // createModal.componentInstance.organization = this.organizationDetails;
    // createModal.componentInstance.masterBuild = null;
    // createModal.result.then(responseFromModal => {
    //   this.isCreating = responseFromModal === REASON.CREATED;
    // });
    // createModal.closed.subscribe(() => {
    //   this.loadPage();
    // });
  }

  private onSuccess(departmentsTargets: Departments[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));

    const numPage = getNumPage(this.totalItemsCreated, this.itemsPerPage);

    if (this.isCreating && this.page !== numPage) {
      this.isCreating = false;
      this.predicate = 'ee.id';
      this.ascending = true;
      this.page = numPage;
      // this.loadPage();
    }

    this.departmentsList = departmentsTargets ?? [];
  }

  private sort(): string[] {
    this.predicate = 'dp.id';
    const result = [this.predicate + ',' + (!this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'dp.id') {
      result.push('dp.id');
    }
    return result;
  }

  get REASON(): typeof REASON {
    return REASON;
  }
}
