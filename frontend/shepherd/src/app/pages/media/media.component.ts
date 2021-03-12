import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.css']
})
export class MediaComponent implements OnInit {

  slug: string = 'TO_FETCH_FROM_ROUTE';

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.slug = this.route.snapshot.paramMap.get('name') || 'err';
  }

}
