import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SpendingCategoryBlockDto} from "../model/backend";


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private BASE_API = "/fe"
  private TARGET = "http://localhost:8081"


  constructor(private httpClient: HttpClient) {
  }


  getSpendingOverviewDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.TARGET + this.BASE_API + "/overview");
  }


}
