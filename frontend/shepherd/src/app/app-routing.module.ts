import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { HowItWorksComponent } from './pages/how-it-works/how-it-works.component';
import { MediaComponent } from './pages/media/media.component';
import { MediasComponent } from './pages/medias/medias.component';

const routes: Routes = [
  { path: 'medias', component: MediasComponent },
  { path: 'medias/:name', component: MediaComponent },
  { path: 'how-it-works', component: HowItWorksComponent },
  { path: '', component: HomepageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
