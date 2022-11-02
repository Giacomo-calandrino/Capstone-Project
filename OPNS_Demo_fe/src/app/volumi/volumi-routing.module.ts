import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { VolumiComponent } from './volumi.component';

const routes: Routes = [{ path: '', component: VolumiComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VolumiRoutingModule { }
