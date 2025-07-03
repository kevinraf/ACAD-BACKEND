import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  let token = '';

  // ✅ Verificamos si estamos en navegador
  if (typeof window !== 'undefined') {
    token = localStorage.getItem('token') || '';
  }

  if (token) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
    return next(cloned);
  }

  return next(req);
};
