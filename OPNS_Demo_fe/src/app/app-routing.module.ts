import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
{ path: '', loadChildren: () => import('./components/home/home.module').then(m => m.HomeModule) },
{ path: 'volumi', canActivate: [AuthGuard], loadChildren: () => import('./components/volumi/volumi.module').then(m => m.VolumiModule) }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
