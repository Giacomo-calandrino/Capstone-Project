import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VolumiRoutingModule } from './volumi-routing.module';
import { VolumiComponent } from './volumi.component';
import { SearchComponent } from '../search/search.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    VolumiComponent,
    SearchComponent
  ],
  imports: [
    CommonModule,
    VolumiRoutingModule,
    FormsModule
  ]
})
export class VolumiModule { }
