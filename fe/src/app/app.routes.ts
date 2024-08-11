import {Routes} from '@angular/router';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    loadComponent: () => import('./page/spending-overview-page/spending-overview-page.component').then(m => m.SpendingOverviewPageComponent)
  },
  {
    path: 'spendings',
    loadComponent: () => import('./component/spending-creation/spending-creation.component').then(m => m.SpendingCreationComponent)
  },
  {
    path: 'rules',
    loadComponent: () => import('./component/rule-creation/rule-creation.component').then(m => m.RuleCreationComponent)
  },
  {
    path: 'transfers',
    loadComponent: () => import('./component/transfer-creation/transfer-creation.component').then(m => m.TransferCreationComponent)
  },

];
