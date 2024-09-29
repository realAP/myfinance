import {Routes} from '@angular/router';
import {authGuard} from "./service/auth-guard/auth-guard.service";

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'spendings',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () => import('./page/login-page/login-page.component').then(m => m.LoginPageComponent),
  },
  {
    path: 'spendings',
    loadComponent: () => import('./page/spending-overview-page/spending-overview-page.component').then(m => m.SpendingOverviewPageComponent),
    canActivate: [authGuard]
  },
  {
    path: 'transfers',
    loadComponent: () => import('./page/transfer-overview-page/transfer-overview-page.component').then(m => m.TransferOverviewPageComponent),
    canActivate: [authGuard]
  },
  {
    path: 'rules',
    loadComponent: () => import('./page/rule-overview-page/rule-overview-page.component').then(m => m.RuleOverviewPageComponent),
    canActivate: [authGuard]
  },
  {
    path: 'backoffice',
    loadComponent: () => import('./page/backoffice-page/backoffice-page.component').then(m => m.BackofficePageComponent),
    canActivate: [authGuard]
  },
  {
    path: '**',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];
