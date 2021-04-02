import { HttpErrorResponse } from '@angular/common/http';
import { AfterContentInit, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { debounceTime, map, filter, distinctUntilChanged } from 'rxjs/operators';
import { PaginatedMedias } from 'src/app/models/medias/paginated-medias';
import { MediaService } from 'src/app/shared/services/media/media.service';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.css']
})
export class MediasComponent implements OnInit, AfterContentInit  {

  public searchForm?: FormGroup;

  public page?: PaginatedMedias;

  public pageIndex = 0;

  constructor(
    private mediaService: MediaService,
  ) { }

  ngOnInit(): void {
    this.loadMedias();

    this.searchForm = new FormGroup({
      search: new FormControl(),
    });
  }

  ngAfterContentInit(): void {
    this.searchForm?.get('search')?.valueChanges.pipe(
      debounceTime(500),
      map((text: string) => text.trim()),
      distinctUntilChanged(),
    ).subscribe(
      (search: string) => this.searchMedias(search),
    );
  }

  private loadMedias(): void {
    this.mediaService.getMedias({
      pageId: this.pageIndex
    }).subscribe(
      (medias: PaginatedMedias) => this.page = medias,
      (err: HttpErrorResponse) => console.log('Unable to fetch medias :' + err)
    );
  }

  loadNextPage(): void {
    ++this.pageIndex;
    this.loadMedias();
  }

  loadPreviousPage(): void {
    --this.pageIndex;
    this.loadMedias();
  }

  private searchMedias(name: string): void {
    this.mediaService.getMedias({
      name: name,
    })
    .subscribe(
      (medias: PaginatedMedias) => {
        this.page = medias;
        // When a result has successfully been fetched, the pagination is
        // reset to fit the newly displayed medias
        this.pageIndex = 0;
      },
      (err: HttpErrorResponse) => console.log('Unable to fetch medias :' + err)
    );
  }
}

