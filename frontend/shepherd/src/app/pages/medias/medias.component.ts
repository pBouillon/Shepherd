import { Component, OnInit } from '@angular/core';
import { Media } from 'src/app/models/medias/Media';

@Component({
  selector: 'app-medias',
  templateUrl: './medias.component.html',
  styleUrls: ['./medias.component.css']
})
export class MediasComponent implements OnInit {

  public medias : Array<Media> = [
    new Media('L\'Ouest Répbulicain', 'lorem ipsum dolor sit amet'),
    new Media('Le canard déchainé', 'lorem ipsum dolor sit amet'),
    new Media('Le Superflu', 'lorem ipsum dolor sit amet'),
    new Media('La Planète', 'lorem ipsum dolor sit amet'),
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
