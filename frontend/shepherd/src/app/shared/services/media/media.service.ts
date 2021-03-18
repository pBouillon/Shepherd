import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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

  getMedias(): Observable<PaginatedMedias> {
    return this.http.get<PaginatedMedias>(`${environment.apiUrl}/medias`)
  }

}
