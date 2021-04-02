import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomepageComponent } from './homepage/homepage.component';
import { MediasComponent } from './medias/medias.component';
import { SharedModule } from '../shared/shared.module';
import { MediaComponent } from './media/media.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CreateMediaComponent } from './create-media/create-media.component';
import { AppRoutingModule } from '../app-routing.module';



@NgModule({
  declarations: [
    HomepageComponent,
    MediasComponent,
    MediaComponent,
    CreateMediaComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
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
