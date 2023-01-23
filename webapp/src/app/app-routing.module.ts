import { EnterpriseComponent } from './admin/customer/enterprise/enterprise.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo:'enterprises', pathMatch: 'full'},
  {
    path: ':enterprise',
    component: EnterpriseComponent,
    data: {
      defaultSort: 'ee.id,asc',
      pageTitle: 'sicpa.home.title',
    }
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
