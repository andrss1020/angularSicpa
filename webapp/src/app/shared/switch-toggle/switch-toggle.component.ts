import { IEnterprises } from './../model/enterprises.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import _ from 'lodash';
import { Status } from '../model/status.model';

@Component({
  selector: 'jhi-switch-toggle',
  templateUrl: './switch-toggle.component.html',
  styleUrls: ['./switch-toggle.component.scss'],
})
export class SwitchToggleComponent implements OnInit {
  @Input() form!: any;
  @Input() controlName!: string;
  @Input() isSettingWithForm?: boolean;
  @Input() titleLabel!: string;
  @Input() showWarnings!: boolean;
  @Input() class!: string;
  @Input() entity?:
    | IEnterprises;
  @Input() isRequired?: boolean;
  @Output() inactiveEntity = new EventEmitter<any>();

  statusAlternativeName = 'statusAlternative';
  ngOnInit(): void {
    this.statusAlternativeName = this.statusAlternativeName + this.controlName;
    this.form.addControl(this.statusAlternativeName, new FormControl(true, [Validators.required]));
    this.form.get(this.controlName)?.valueChanges.subscribe((value: Status) => {
      this.form.get(this.statusAlternativeName).setValue(value === Status.ACTIVE, { emitEvent: false });
    });
    this.form.get(this.statusAlternativeName)?.valueChanges.subscribe((value: boolean) => {
      this.changeEvent(value);
    });
  }

  changeEvent(event: boolean): void {
    if (_.isNil(this.isSettingWithForm) || !this.isSettingWithForm) {
      this.changeEventStatus(event);
    } else {
      this.changeEventìnFormByControlName(event);
    }
  }

  changeEventìnFormByControlName(event: boolean): void {
    if (_.isNil(this.form) || _.isNil(this.controlName)) {
      return;
    }
    const _controlNameValue = this.form.get(this.controlName).value;
    if (!_.isNil(_controlNameValue) && _controlNameValue === Status.ACTIVE && event === false) {
      this.form.get(this.controlName).setValue(Status.INACTIVE);
    }
    if (!_.isNil(_controlNameValue) && _controlNameValue === Status.INACTIVE && event === true) {
      this.form.get(this.controlName).setValue(Status.ACTIVE);
    }
  }

  changeEventStatus(event: boolean): void {
    if (!_.isNil(this.entity?.status) && this.entity?.status === Status.ACTIVE && event === false) {
      this.executeInactiveEntity(this.entity);
    }
    if (!_.isNil(this.entity?.status) && this.entity?.status === Status.INACTIVE && event === true) {
      this.form.patchValue({
        status: Status.ACTIVE,
      });
    }
    if (_.isNil(this.entity?.status)) {
      this.form.patchValue({
        status: event === false ? Status.INACTIVE : Status.ACTIVE,
      });
    }
  }

  executeInactiveEntity(
    entity: IEnterprises
  ): void {
    this.inactiveEntity.emit(entity);
  }

  get isRequiredStatus(): boolean {
    return _.isNil(this.isRequired) ? true : this.isRequired;
  }
}
