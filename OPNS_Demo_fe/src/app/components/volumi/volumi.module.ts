import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VolumiRoutingModule } from './volumi-routing.module';
import { VolumiComponent } from './volumi.component';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';


@NgModule({
  declarations: [
    VolumiComponent
  ],
  imports: [
    CommonModule,
    VolumiRoutingModule,
    FormsModule,
    NgxPaginationModule
  ]
})
export class VolumiModule { }
