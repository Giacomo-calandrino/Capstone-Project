import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VolumiRoutingModule } from './volumi-routing.module';
import { VolumiComponent } from './volumi.component';


@NgModule({
  declarations: [
    VolumiComponent
  ],
  imports: [
    CommonModule,
    VolumiRoutingModule
  ]
})
export class VolumiModule { }
