import {Routes} from '@angular/router';
import {authGuard} from "./service/auth-guard/auth-guard";

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'spendings',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () => import('./page/login-page/login-page').then(m => m.LoginPage),
  },
  {
    path: 'spendings',
    loadComponent: () => import('./page/spending-overview-page/spending-overview-page').then(m => m.SpendingOverviewPage),
    canActivate: [authGuard]
  },
  {
    path: 'transfers',
    loadComponent: () => import('./page/transfer-overview-page/transfer-overview-page').then(m => m.TransferOverviewPage),
    canActivate: [authGuard]
  },
  {
    path: 'rules',
    loadComponent: () => import('./page/rule-overview-page/rule-overview-page').then(m => m.RuleOverviewPage),
    canActivate: [authGuard]
  },
  {
    path: 'incomes',
    loadComponent: () => import('./page/income-overview-page/income-overview-page').then(m => m.IncomeOverviewPage),
    canActivate: [authGuard]
  },
  {
    path: 'backoffice',
    loadComponent: () => import('./page/backoffice-page/backoffice-page').then(m => m.BackofficePage),
    canActivate: [authGuard]
  },
  {
    path: '**',
    redirectTo: 'login',
    pathMatch: 'full'
  }
];
