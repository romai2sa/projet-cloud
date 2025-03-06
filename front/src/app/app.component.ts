import { Component } from '@angular/core';
import {Router, RouterOutlet} from '@angular/router';
import {CommonModule, NgFor} from '@angular/common';
import {ApiService} from './services/api.service';
import {Film} from './models/Film';
import { MyMonitoringService } from './services/logging.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'frontend';
  constructor( private apiService: ApiService, private router: Router) {}

  random() {
    let listeFilm: Film[]
    this.apiService.getFilms().subscribe({
      next: (films) => {
        listeFilm = films
      }, complete: () => {
        listeFilm = [...listeFilm]
        let i = Math.floor(Math.random() * (listeFilm.length + 1));
        this.router.navigate(['/film/' + listeFilm[i].id])
      }
    })
  }
}
