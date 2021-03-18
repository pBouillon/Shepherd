import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { AppRoutingModule } from '../app-routing.module';
import { MediaCardComponent } from './media-card/media-card.component';



@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    MediaCardComponent,
  ],
  imports: [
    CommonModule,
    AppRoutingModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    MediaCardComponent
  ]
})
export class SharedModule { }
