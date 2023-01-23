import { Status } from './../../../../shared/model/status.model';
import { IEnterprises } from './../../../../shared/model/enterprises.model';
import { EnterpriseService } from './../../../../services/enterprise.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import _ from 'lodash';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { REASON } from 'src/app/shared/constants/reason.constants';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  @Input() enterprisesId!: number;

  public enterprises!: IEnterprises;
  isRequiredName=true;

  enterprisesForm = this.fb.group({
    id: [null],
    status: [Status.ACTIVE, [Validators.required]],
    address: [false, [Validators.required]],
    name: [
      null,
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(255),
      ],
    ],
    phone: [false, [Validators.required]],
  });

  constructor(
    private fb: FormBuilder,
    protected enterpriseService: EnterpriseService,
    public modal: NgbActiveModal,
    ) { }

  ngOnInit(): void {
    this.loadEnterprise(this.enterprisesId);
  }

  closeModal(reason: REASON): void {
    this.modal.close(reason);
  }
  inactiveEnterprises(): void {
    this.enterprisesForm.get('status')?.setValue(Status.INACTIVE);
  }
  
  private loadEnterprise(id: number): void {
    if (!_.isNil(id)) {
      this.enterpriseService.getEnterprisesById(id).subscribe((res: any) => {
        this.enterprisesForm.patchValue(res.body);
      });
    }
  }


  get REASON(): typeof REASON {
    return REASON;
  }
}
