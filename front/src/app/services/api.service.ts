import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Film} from '../models/Film';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = environment.apiUrl
  constructor( private http: HttpClient ) { }

  getFilms() : Observable<Film[]> {
    return this.http.get<Film[]>(this.apiUrl + '/films');
  }

  getFilm(id: string): Observable<Film> {
    return this.http.get<Film>(this.apiUrl + '/films/' + id);
  }

}
