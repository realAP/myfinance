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
    path: 'spending',
    loadComponent: () => import('./page/spending-creation-page/spending-creation-page.component').then(m => m.SpendingCreationPageComponent)
  }
];
