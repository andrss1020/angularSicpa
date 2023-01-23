import { BUTTON_CLASS, BUTTON_TYPE } from './../../model/enumeration/type-btn.model';
import { Component, EventEmitter, Input, Output } from '@angular/core';


@Component({
  selector: 'jhi-custom-button',
  templateUrl: './custom-button.component.html',
  styleUrls: ['./custom-button.component.scss'],
})
export class CustomButtonComponent {
  @Input() typeButton!: string | BUTTON_TYPE;
  @Input() idButton!: string;
  @Input() classButton!: string;
  @Input() translationText!: string;
  @Input() disabled!: boolean;
  @Input() isLoading!: boolean;
  @Output() clickButton = new EventEmitter<void>();

  private hashTableClassBtn = BUTTON_CLASS;

  validateSubmit(): string {
    if (
      (this.typeButton === 'submit' || this.typeButton === this.BUTTON_TYPE.CIRCLE_SUBMIT || this.typeButton === BUTTON_TYPE.SQUARE_SUBMIT) &&
      !this.disabled
    ) {
      return 'submit';
    }
    return 'button';
  }

  onClick(): void {
    if (!this.disabled) {
      this.clickButton.emit();
    }
  }

  get classBtn(): string {
    if (this.classButton) {
      return this.classButton;
    }
    const buttonType = this.typeButton.toUpperCase() as BUTTON_TYPE;
    return this.hashTableClassBtn[buttonType];
  }

  get BUTTON_TYPE(): typeof BUTTON_TYPE {
    return BUTTON_TYPE;
  }
}
