import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {tokenInterceptor} from './core/interceptors/token-interceptor';
import {urlInterceptor} from './core/interceptors/url-interceptor';
import {catchInterceptor} from './core/interceptors/catch-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes), provideClientHydration(), provideHttpClient(
    withInterceptors([
      tokenInterceptor,
      urlInterceptor,
      catchInterceptor
    ])
  )]
};
