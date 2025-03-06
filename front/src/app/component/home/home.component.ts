import {Component, OnInit} from '@angular/core';
import {NgFor} from '@angular/common';
import {ApiService} from '../../services/api.service';
import {Film} from '../../models/Film';

@Component({
  selector: 'app-film',
  standalone: true,
  imports: [NgFor],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  films: Film[] = [];

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.getFilms()
  }

  getFilms() {
    this.apiService.getFilms().subscribe({
      next: films => {
        this.films = films
      }, complete: () => {
        this.getDetailFilms()
      }
    })
  }

  getDetailFilms() {
    this.films.forEach(film => {
      this.apiService.getFilm(film.id).subscribe({
        next: (filDetaille) => {
          film.image = filDetaille.image
        }, complete: () => {
          this.films = [...this.films]
        }
      })
    })
  }

}
