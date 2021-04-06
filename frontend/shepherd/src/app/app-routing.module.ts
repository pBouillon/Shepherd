import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateMediaComponent } from './pages/create-media/create-media.component';

import { HomepageComponent } from './pages/homepage/homepage.component';
import { MediaComponent } from './pages/media/media.component';
import { MediasComponent } from './pages/medias/medias.component';
import { MediaResolver } from './shared/resolvers/media.resolver';

const routes: Routes = [
  { path: 'medias', component: MediasComponent },
  { path: 'medias/new', component: CreateMediaComponent },
  { path: 'medias/details/:name', component: MediaComponent, resolve: { media: MediaResolver } },
  { path: '', component: HomepageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
