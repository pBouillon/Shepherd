import { Pageable } from "../pagination/pageable";
import { Media } from "./media";

export class PaginatedMedias {

    public content: Array<Media> = [];

    public empty: boolean = true;

    public first: boolean = true;

    public last: boolean = true;

    public number: number = 0;

    public numberOfElements: number = 0;

    public pageable: Pageable = new Pageable();
    
    public size: number = 0;
    
    public totalPages: number = 0;
    
    public totalElements: number = 0;

}
