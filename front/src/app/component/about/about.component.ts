import {Component, OnInit} from '@angular/core';
import {NgFor} from '@angular/common';
import {ApiService} from '../../services/api.service';
import {Film} from '../../models/Film';

@Component({
  selector: 'app-film',
  standalone: true,
  imports: [],
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.scss']
})
export class AboutComponent implements OnInit {


  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

}
