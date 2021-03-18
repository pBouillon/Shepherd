import { Sort } from "./sort";

export class Pageable {

  public offset: number = 0;

  public paged: boolean = true;
  
  public pageNumber: number = 0;
  
  public pageSize: number = 10;

  public sort: Sort = new Sort();

  public unpaged: boolean = true;

}
