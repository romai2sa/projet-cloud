import {RouterModule, Routes} from '@angular/router';
import {FilmComponent} from './component/film/film.component';
import {AppComponent} from './app.component';
import {NgModule} from '@angular/core';
import {HomeComponent} from './component/home/home.component';
import {AboutComponent} from './component/about/about.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'film/:id', component: FilmComponent },
  { path: 'about', component: AboutComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
