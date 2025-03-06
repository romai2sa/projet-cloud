export class Film {

    id!:string;
    title!:string;
    original_title!:string;
    original_title_romanised!:string;
    director!:string;
    producer!:string;
    release_date!:string;
    running_time!:string;
    rt_score!:string;
    image?:string
    movie_banner?: string;
    description?: string;
    people?: string[];
    species?: string[];
    locations?: string[];
    vehicles?: string[];
    url?: string;

}
