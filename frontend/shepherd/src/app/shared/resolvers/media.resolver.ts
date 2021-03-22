import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

import { Media } from 'src/app/models/medias/media';
import { MediaService } from '../services/media/media.service';

@Injectable({
  providedIn: 'root'
})
export class MediaResolver implements Resolve<Media | null> {

  constructor(
    private mediaService: MediaService,
    private router: Router,
  ) { }

  resolve(
    route: ActivatedRouteSnapshot,
    _state: RouterStateSnapshot
  ): Observable<Media | null> {
    const name = route.paramMap.get('name')!;
    return this.mediaService.getMediaByName(name).pipe(
      tap(media => {
        if (!media) this.router.navigate(['medias'])
      })
    );
  }
}
