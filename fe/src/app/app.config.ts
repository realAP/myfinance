import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors, withXhr} from "@angular/common/http";
import {provideAnimations} from "@angular/platform-browser/animations";
import {MessageService} from "primeng/api";
import {providePrimeNG} from "primeng/config";
import Aura from "@primeuix/themes/aura";
import {httpErrorInterceptorInterceptor} from "./service/http-error-interceptor/http-error-interceptor";
import {authInterceptor} from "./service/authentication-interceptor/authentication-interceptor";

export const appConfig: ApplicationConfig = {
  providers: [
    MessageService,
    providePrimeNG({theme: {preset: Aura}}),
    provideAnimations(),
    provideHttpClient(withXhr(), withInterceptors([httpErrorInterceptorInterceptor,authInterceptor])),
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes)]
};
