import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PaginatedMedias } from 'src/app/models/medias/paginated-medias';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  constructor(
    private http: HttpClient
  ) { }

  getMedias(params?: {
    itemsPerPages?: number,
    name?: string,
    pageId?: number,
    website?: string
  }): Observable<PaginatedMedias> {
    let httpParams = new HttpParams();

    if (params?.itemsPerPages) 
      httpParams = httpParams.set('itemsPerPages', params.itemsPerPages + '');

    if (params?.name)
      httpParams = httpParams.set('name', params.name);

    if (params?.pageId)
      httpParams = httpParams.set('pageId', params.pageId + '');

    if (params?.website)
      httpParams = httpParams.set('website', params.website);

    return this.http.get<PaginatedMedias>(`${environment.apiUrl}/medias`, {
      params: httpParams
    });
  }
}
