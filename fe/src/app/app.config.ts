import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {provideAnimations} from "@angular/platform-browser/animations";
import {MessageService} from "primeng/api";
import {httpErrorInterceptorInterceptor} from "./service/http-error-interceptor/http-error-interceptor.interceptor";
import {authInterceptor} from "./service/authentication-interceptor/authentication-interceptor.service";

export const appConfig: ApplicationConfig = {
  providers: [
    MessageService,
    provideAnimations(),
    provideHttpClient(withInterceptors([httpErrorInterceptorInterceptor,authInterceptor])),
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes)]
};
