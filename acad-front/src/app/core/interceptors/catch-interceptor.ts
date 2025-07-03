import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const catchInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      console.error('Error HTTP:', error);
      alert(`Error ${error.status}: ${error.statusText}`);
      return throwError(() => error);
    })
  );
};
