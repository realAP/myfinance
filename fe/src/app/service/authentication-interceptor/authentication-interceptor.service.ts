import { inject } from '@angular/core';
import { HttpInterceptorFn } from '@angular/common/http';
import {AuthService} from "../authentication/auth.service";

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);

  // Get the authorization header value from the AuthService
  const authHeader = authService.getAuthorizationHeader();

  // Clone the request to add the authorization header, if available
  const authReq = authHeader
    ? req.clone({
      setHeaders: {
        Authorization: authHeader
      }
    })
    : req;  // If no auth header, proceed with the original request

  // Pass the modified request to the next handler
  return next(authReq);
};
