import { Pageable } from "../pagination/pageable";
import { Media } from "./media";

export class PaginatedMedias {

    constructor(
        public content: Array<Media>,
        public empty: boolean,
        public first: boolean,
        public last: boolean,
        public number: number,
        public numberOfElements: number,
        public pageable: Pageable,
        public size: number,
        public totalPages: number,
        public totalElements: number,
    ) { }

}
