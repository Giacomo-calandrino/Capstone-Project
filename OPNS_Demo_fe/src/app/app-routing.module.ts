import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
{ path: '', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) },
{ path: 'volumi', loadChildren: () => import('./volumi/volumi.module').then(m => m.VolumiModule) }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
