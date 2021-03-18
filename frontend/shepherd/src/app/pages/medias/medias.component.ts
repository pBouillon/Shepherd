import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { PaginatedMedias } from 'src/app/models/medias/paginated-medias';
import { MediaService } from 'src/app/shared/services/media/media.service';
import { Media } from '../../models/medias/media';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.css']
})
export class MediasComponent implements OnInit {

  public pageIndex = 0;

  public page: PaginatedMedias = new PaginatedMedias();

  constructor(
    private mediaService: MediaService,
  ) { }

  ngOnInit(): void {
    this.loadMedias();
  }

  private loadMedias(): void {
    this.mediaService.getMedias({ pageId: this.pageIndex })
      .subscribe(
        (medias: PaginatedMedias) => this.page = medias,
        (err: HttpErrorResponse) => console.log('Unable to fetch medias :' + err)
      );
  }

  searchNextPage(): void {
    ++this.pageIndex;
    this.loadMedias();
  }

  searchPreviousPage(): void {
    --this.pageIndex;
    this.loadMedias();
  }
}
