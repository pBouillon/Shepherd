import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AppRoutingModule } from '../app-routing.module';
import { MediaCardComponent } from './media-card/media-card.component';
import { MediaRatingComponent } from './media-rating/media-rating.component';
import { GaugeModule } from 'angular-gauge';



@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    MediaCardComponent,
    MediaRatingComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    GaugeModule.forRoot({
      label: (value) => value+'%',
      animated: true,
      dialRadius: 40,
      max: 100,
      dialStartAngle: 140,
      dialEndAngle: 40,
    }),
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    MediaCardComponent,
    MediaRatingComponent,
  ]
})
export class SharedModule { }
