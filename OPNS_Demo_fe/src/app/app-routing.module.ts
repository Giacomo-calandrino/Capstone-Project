import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
{ path: '', loadChildren: () => import('./home/home.module').then(m => m.HomeModule) }, 
{ path: 'login', loadChildren: () => import('./login/login.module').then(m => m.LoginModule) }, 
{ path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule) }, 
{ path: 'info', loadChildren: () => import('./info/info.module').then(m => m.InfoModule) },
{ path: 'volumi', loadChildren: () => import('./volumi/volumi.module').then(m => m.VolumiModule) }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
