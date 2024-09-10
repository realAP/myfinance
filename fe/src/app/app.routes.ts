import {Routes} from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'spendings',
    loadComponent: () => import('./page/spending-overview-page/spending-overview-page.component').then(m => m.SpendingOverviewPageComponent)
  },
  {
    path: 'transfers',
    loadComponent: () => import('./page/transfer-overview-page/transfer-overview-page.component').then(m => m.TransferOverviewPageComponent)
  },
  {
    path: 'rules',
    loadComponent: () => import('./page/rule-overview-page/rule-overview-page.component').then(m => m.RuleOverviewPageComponent)
  },
  {
    path: 'backoffice',
    loadComponent: () => import('./page/backoffice-page/backoffice-page.component').then(m => m.BackofficePageComponent)
  }

];
