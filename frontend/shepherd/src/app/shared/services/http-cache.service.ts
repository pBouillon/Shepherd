import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpCacheService {

  private readonly requests = new Map<string, HttpResponse<any>>();

  constructor() {
    this.requests = new Map();
  }

  get(url: string): HttpResponse<any> | undefined {
    return this.requests.get(url);
  }

  invalidate(): void {
    this.requests.clear();
  }

  put(url: string, response: HttpResponse<any>): void {
    this.requests.set(url, response);
  }

}
