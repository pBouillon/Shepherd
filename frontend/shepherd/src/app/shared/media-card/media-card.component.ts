import { Component, Input, OnInit } from '@angular/core';
import { Media } from '../../models/medias/media';

@Component({
  selector: 'app-media-card',
  templateUrl: './media-card.component.html',
  styleUrls: ['./media-card.component.css']
})
export class MediaCardComponent implements OnInit {

  @Input()
  public media!: Media;

  constructor() { }

  ngOnInit(): void {
  }

}
