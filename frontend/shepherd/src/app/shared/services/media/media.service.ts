import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { PaginatedMedias } from 'src/app/models/medias/paginated-medias';
import { Media } from 'src/app/models/medias/media';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MediaService {

  constructor(
    private http: HttpClient
  ) { }

  createMedia(media: Media): Observable<Media> {
    return this.http.post<Media>(`${environment.apiUri}/medias`, media);
  }

  getMediaByName(name: string): Observable<Media | null> {
    const params = new HttpParams().set('name', name);

    return this.http.get<PaginatedMedias>(`${environment.apiUri}/medias`, {
      params: params,
    }).pipe(
      map((page: PaginatedMedias) => page.totalElements === 1
        ? page.content[0]
        : null),
    );
  }

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

    return this.http.get<PaginatedMedias>(`${environment.apiUri}/medias`, {
      params: httpParams
    });
  }

}
