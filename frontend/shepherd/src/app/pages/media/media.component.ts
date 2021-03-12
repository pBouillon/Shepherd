import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-media',
  templateUrl: './media.component.html',
  styleUrls: ['./media.component.css']
})
export class MediaComponent implements OnInit {

  slang: string = 'TO_FETCH_FROM_ROUTE';

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.slang = this.route.snapshot.paramMap.get('name') || 'err';
  }

}
