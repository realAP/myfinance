import {inject} from '@angular/core';
import {CanActivateFn, Router} from "@angular/router";
import {AuthService} from "../authentication/auth.service";

export const authGuard: CanActivateFn = () => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (authService.isAuthenticated()) {
    return true;  // Allow navigation if authenticated
  } else {
    router.navigate(['/login']);  // Redirect to login if not authenticated
    return false;
  }
};
