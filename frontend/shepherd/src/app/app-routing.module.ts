import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { MediaComponent } from './pages/media/media.component';
import { MediasComponent } from './pages/medias/medias.component';

const routes: Routes = [
  { path: 'medias', component: MediasComponent },
  { path: 'medias/:name', component: MediaComponent },
  { path: '', component: HomepageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
