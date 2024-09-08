import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {
  BankCreationDto,
  BankDto,
  CategoryCreationDto,
  CategoryDto,
  RuleCreationDto,
  RuleDto,
  SpaceCreationDto,
  SpaceDto,
  SpendingCategoryBlockDto,
  SpendingCreationDto,
  TransferCreationDto,
  TransferDto
} from "../model/backend";


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private BASE_API = "/fe"
  private TARGET = "http://localhost:8080"


  constructor(private httpClient: HttpClient) {
  }

  //
  // TODO: add error handling
  //

  getSpendingCategoryBlockDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.TARGET + this.BASE_API + "/overview");
  }

  createSpace(name: string): void {
    const spaceCreationDto: SpaceCreationDto = {name: name};
    this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spaces", spaceCreationDto).subscribe();
  }

  getSpaces(): Observable<SpaceDto[]> {
    return this.httpClient.get<SpaceDto[]>(this.TARGET + this.BASE_API + "/crud/spaces");
  }

  createBank(name: string): void {
    const bankCreationDto: BankCreationDto = {name: name};
    this.httpClient.post(this.TARGET + this.BASE_API + "/crud/banks", bankCreationDto).subscribe();
  }

  getBanks(): Observable<BankDto[]> {
    return this.httpClient.get<BankDto[]>(this.TARGET + this.BASE_API + "/crud/banks");
  }

  createCategory(name: string) {
    const categoryCreationDto: CategoryCreationDto = {name: name};
    this.httpClient.post(this.TARGET + this.BASE_API + "/crud/categories", categoryCreationDto).subscribe();
  }

  getCategories(): Observable<CategoryDto[]> {
    return this.httpClient.get<CategoryDto[]>(this.TARGET + this.BASE_API + "/crud/categories");
  }

  createRule(ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.TARGET + this.BASE_API + "/crud/rules", ruleCreationDto);
  }

  getRules(): Observable<RuleDto[]> {
    return this.httpClient.get<RuleDto[]>(this.TARGET + this.BASE_API + "/crud/rules");
  }

  createTransfer(transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.TARGET + this.BASE_API + "/crud/transfers", transferCreationDto);
  }

  editTransfer(transferId: number, transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.TARGET + this.BASE_API + "/crud/transfers/" + transferId , transferCreationDto);
  }

  getTransfers(): Observable<TransferDto[]> {
    return this.httpClient.get<TransferDto[]>(this.TARGET + this.BASE_API + "/crud/transfers");
  }

  createSpending(spendingCreationDto: SpendingCreationDto) {
    this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spendings", spendingCreationDto).subscribe();
  }

  confirmChange(id: number) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/transfers/" + id + "/confirmchange", {});
  }
}
