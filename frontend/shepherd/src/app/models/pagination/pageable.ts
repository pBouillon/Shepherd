import { Sort } from "./sort";

export class Pageable {

  constructor(
    public offset: number,
    public paged: boolean,
    public pageNumber: number,
    public pageSize: number,
    public sort: Sort,
    public unpaged: boolean,
  ) { }

}
