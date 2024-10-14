import {HttpErrorResponse, HttpInterceptorFn} from '@angular/common/http';
import {inject} from "@angular/core";
import {catchError, throwError} from "rxjs";
import {MessageService} from "primeng/api";

export const httpErrorInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  const messageService = inject(MessageService);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      // Display the error message in a PrimeNG toast
      messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: error.message || 'An unknown error occurred',
      });

      return throwError(() => error);
    })
  );
};
