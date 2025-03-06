import {Component, OnInit} from '@angular/core';
import {NgFor} from '@angular/common';
import {ApiService} from '../../services/api.service';
import {Film} from '../../models/Film';
import {ActivatedRoute, RouterLink} from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-film',
  standalone: true,
  templateUrl: './film.component.html',
  styleUrls: ['./film.component.scss']
})
export class FilmComponent implements OnInit {
  private routeSub!: Subscription;

  film: Film = {
    director: '',
    id: '',
    original_title: '',
    original_title_romanised: '',
    producer: '',
    release_date: '',
    rt_score: '',
    running_time: '',
    title: ''
  }

  idFilm: string = ""

  constructor(private apiService: ApiService,
              private route: ActivatedRoute ) {
  }

  ngOnInit(): void {
    this.routeSub = this.route.paramMap.subscribe((params) => {
      this.idFilm = params.get('id')!;
      this.getFilm(this.idFilm);
    });
  }

  ngOnDestroy(): void {
    if (this.routeSub) {
      this.routeSub.unsubscribe();
    }
  }

  getFilm(id: string) {
    this.apiService.getFilm(id).subscribe({
      next: film => {
        this.film = film
      }
    })
  }

}
