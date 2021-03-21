import { Component, Input, OnInit } from '@angular/core';

import { TrustReport } from 'src/app/models/medias/trust-report';

@Component({
  selector: 'app-media-rating',
  templateUrl: './media-rating.component.html',
  styleUrls: ['./media-rating.component.css']
})
export class MediaRatingComponent implements OnInit {

  @Input()
  public report!: TrustReport;

  constructor() { }

  ngOnInit(): void {
  }

}
