import { HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpCacheService {

  /**
   * Default time to live for an entry
   */
  private readonly TTL_MS = 5_000;

  /**
   * Map to keep track of when the entry was created
   */
  private readonly creationTimes = new Map<string, number>();

  /**
   * Map to keep track of the response for a given request
   */
  private readonly requests = new Map<string, HttpResponse<any>>();

  /**
   * Get the cached response for a URL if any
   * @param url - URL requested
   * @returns The response if any is cached, undefined otherwise
   */
  get(url: string): HttpResponse<any> | undefined {
    this.invalidateIfOutdated(url);
    return this.requests.get(url);
  }

  /**
   * Invalidate all the entries
   */
  invalidate(): void {
    this.creationTimes.clear();
    this.requests.clear();
  }

  /**
   * Invalidate the cached response associated to the provided URL if it is outdated
   * @param url - The URL to check
   */
  invalidateIfOutdated(url: string): void {
    const createdAt = this.creationTimes.get(url);
    if (createdAt && createdAt + this.TTL_MS <= Date.now()) {
        this.invalidateUrl(url);
    }
  }

  /**
   * Invalidate a specific URL
   * @param url - The URL to invalidate
   */
  invalidateUrl(url: string): void {
    this.creationTimes.delete(url);
    this.requests.delete(url);
  }

  /**
   * Cache the response for a specific URL
   * @param url - The URL for whom the response to cache was returned
   * @param response The response to cache
   */
  put(url: string, response: HttpResponse<any>): void {
    this.creationTimes.set(url, Date.now());
    this.requests.set(url, response);
  }

}
