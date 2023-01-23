import { SortDirective } from './shared/sort/sort.directive';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EnterpriseComponent } from './admin/customer/enterprise/enterprise.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CustomButtonComponent } from './shared/button/custom-button/custom-button.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule  } from '@angular/material/button';
import { DetailComponent } from './admin/customer/enterprise/detail/detail.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SwitchToggleComponent } from './shared/switch-toggle/switch-toggle.component';
import { DepartmentComponent } from './admin/customer/department/department.component';
@NgModule({
  declarations: [
    EnterpriseComponent,
    AppComponent,
    CustomButtonComponent,
    SortDirective,
    DetailComponent,
    SwitchToggleComponent,
    DepartmentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatProgressBarModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatGridListModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  exports: [
    SortDirective,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {  }
