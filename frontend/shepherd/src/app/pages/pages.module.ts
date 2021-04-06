import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import
 { ToastrModule } from 'ngx-toastr';

import { HomepageComponent } from './homepage/homepage.component';
import { MediasComponent } from './medias/medias.component';
import { SharedModule } from '../shared/shared.module';
import { MediaComponent } from './media/media.component';
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
    ToastrModule,
  ],
  exports: [
    HomepageComponent,
    MediasComponent,
    MediaComponent,
  ]
})
export class PagesModule { }
