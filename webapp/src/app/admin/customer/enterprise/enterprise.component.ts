import { MODAL_EXTRA_LARGE_CONSTANT_COMMON } from './../../../shared/constants/modals.constants';

import { IEnterprises, Enterprises } from './../../../shared/model/enterprises.model';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { EnterpriseService } from './../../../services/enterprise.service';
import { Component, OnInit } from '@angular/core';
import { getNumPage } from 'src/app/util/pagination-util';
import { BUTTON_TYPE } from 'src/app/shared/model/enumeration/type-btn.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DetailComponent } from './detail/detail.component';
import { REASON } from 'src/app/shared/constants/reason.constants';

@Component({
  selector: 'app-enterprise',
  templateUrl: './enterprise.component.html',
  styleUrls: ['./enterprise.component.scss']
})
export class EnterpriseComponent implements OnInit {
  page = 0;
  itemsPerPage = 5;
  predicate!: string;
  ascending!: boolean;
  totalItems = 0;
  totalItemsCreated = 0;
  isCreating = false;
  maxSize = 5;
  enterprisesList: IEnterprises[] | null = null;

  constructor(
    protected enterpriseService: EnterpriseService,
    protected modalService: NgbModal,
    ) { }

  ngOnInit(): void {
    this.loadPage(this.page);
  }

  loadPage(page?: number): void {
    const pageToLoad: number = page ?? this.page;
    this.enterpriseService
      .loadPage({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IEnterprises[]>) => this.onSuccess(res.body, res.headers));
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

  private onSuccess(enterprisesTargets: Enterprises[] | null, headers: HttpHeaders): void {
    this.totalItems = Number(headers.get('X-Total-Count'));

    const numPage = getNumPage(this.totalItemsCreated, this.itemsPerPage);

    if (this.isCreating && this.page !== numPage) {
      this.isCreating = false;
      this.predicate = 'ee.id';
      this.ascending = true;
      this.page = numPage;
      // this.loadPage();
    }

    this.enterprisesList = enterprisesTargets ?? [];
  }

  private sort(): string[] {
    this.predicate = 'ee.id';
    const result = [this.predicate + ',' + (!this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'ee.id') {
      result.push('ee.id');
    }
    return result;
  }
  get classColumn(): string {
    return 'text-nowrap custom-width-120';
  }
  get BUTTON_TYPE(): typeof BUTTON_TYPE {
    return BUTTON_TYPE;
  }
  get REASON(): typeof REASON {
    return REASON;
  }
}
