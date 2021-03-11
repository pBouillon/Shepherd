import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomepageComponent } from './homepage/homepage.component';
import { HowItWorksComponent } from './how-it-works/how-it-works.component';
import { MediasComponent } from './medias/medias.component';



@NgModule({
  declarations: [
    HomepageComponent,
    HowItWorksComponent,
    MediasComponent,
  ],
  imports: [
    CommonModule
  ],
  exports: [
    HomepageComponent,
  ]
})
export class PagesModule { }
