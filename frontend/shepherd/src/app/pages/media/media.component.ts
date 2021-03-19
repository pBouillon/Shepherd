import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Media } from 'src/app/models/medias/media';
import { MediaService } from 'src/app/shared/services/media/media.service';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.css']
})
export class MediaComponent implements OnInit {

  media?: Media;

  constructor(
    private mediaService: MediaService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    const name = this.route.snapshot.paramMap.get('name')!;

    this.mediaService.getMediaByName(name).subscribe(
      (media: Media) => this.media = media,
      (error: HttpErrorResponse) => console.log(`Unable to retrieve the media: ${error}`),
    );
  }

}
