import {HttpInterceptorFn} from '@angular/common/http';
import {environment} from '../../../environments/environment';

export const urlInterceptor: HttpInterceptorFn = (req, next) => {
  if (!req.url.startsWith('http')) {
    const apiReq = req.clone({
      url: `${environment.apiUrl}/${req.url}`
    });
    return next(apiReq);
  }

  return next(req);
};
