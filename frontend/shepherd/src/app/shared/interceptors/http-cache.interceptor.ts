import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse,
} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { HttpCacheService } from '../services/http-cache.service';
import { tap } from 'rxjs/operators';

@Injectable()
export class HttpCacheInterceptor implements HttpInterceptor {

  constructor(
    private readonly cache: HttpCacheService,
  ) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (request.method !== 'GET') {
      this.cache.invalidate();
      return next.handle(request);
    }

    const cachedResponse = this.cache.get(request.url);

    if (cachedResponse) {
      return of(cachedResponse);
    }

    return next.handle(request).pipe(
      tap(response => {
        if (response instanceof HttpResponse) {
          this.cache.put(request.url, response);
        }
      })
    );
  }
}
