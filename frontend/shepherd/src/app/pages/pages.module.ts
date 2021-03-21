import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageComponent } from './homepage/homepage.component';
import { MediasComponent } from './medias/medias.component';
import { SharedModule } from '../shared/shared.module';
import { MediaComponent } from './media/media.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    HomepageComponent,
    MediasComponent,
    MediaComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    HttpClientModule,
  ],
  exports: [
    HomepageComponent,
    MediasComponent,
    MediaComponent,
  ]
})
export class PagesModule { }
