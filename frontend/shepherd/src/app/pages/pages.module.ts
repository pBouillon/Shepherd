import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomepageComponent } from './homepage/homepage.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { MediasComponent } from './medias/medias.component';
import { SharedModule } from '../shared/shared.module';
import { MediaComponent } from './media/media.component';



@NgModule({
  declarations: [
    HomepageComponent,
    HowItWorksComponent,
    MediasComponent,
    MediaComponent,
  ],
  imports: [
    CommonModule,
    SharedModule,
  ],
  exports: [
    HomepageComponent,
    HowItWorksComponent,
    MediasComponent,
    MediaComponent,
  ]
})
export class PagesModule { }
