import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SpaceCreationDto, SpendingCategoryBlockDto} from "../model/backend";


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private BASE_API = "/fe"
  private TARGET = "http://localhost:8080"


  constructor(private httpClient: HttpClient) {
  }

  getSpendingCategoryBlockDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.TARGET + this.BASE_API + "/overview");
  }


  createSpace(name: string): void {
    const spaceCreationDto: SpaceCreationDto = {name: name};
    this.httpClient.post(this.TARGET + this.BASE_API + "/write/spaces", spaceCreationDto).subscribe(() => console.log("Space created"));
    ;
  }

}
