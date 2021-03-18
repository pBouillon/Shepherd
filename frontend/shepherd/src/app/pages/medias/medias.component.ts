import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MediaService } from 'src/app/shared/services/media/media.service';
import { Media } from '../../models/medias/media';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.css']
})
export class MediasComponent implements OnInit {

  public medias : Array<Media> = [];

  constructor(
    private mediaService: MediaService,
  ) { }

  ngOnInit(): void {
    this.mediaService.getMedias()
      .subscribe(
        medias => this.medias = medias.content,
        // TODO: proper error handling
        (err: HttpErrorResponse) => console.log('Unable to fetch medias :' + err)
      );
  }

}
