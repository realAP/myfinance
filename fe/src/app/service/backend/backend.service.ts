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
} from "../../model/backend";
import {environment} from "../../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class BackendService {

  private BASE_API = "/fe"
  private TARGET = environment.apiUrl;


  constructor(private httpClient: HttpClient) {
  }

  //
  // TODO: add error handling
  //

  getSpendingCategoryBlockDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.TARGET + this.BASE_API + "/overview");
  }

  createSpace(name: string): Observable<Object> {
    const spaceCreationDto: SpaceCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spaces", spaceCreationDto);
  }

  getSpaces(): Observable<SpaceDto[]> {
    return this.httpClient.get<SpaceDto[]>(this.TARGET + this.BASE_API + "/crud/spaces");
  }

  createBank(name: string): Observable<Object> {
    const bankCreationDto: BankCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/banks", bankCreationDto);
  }

  getBanks(): Observable<BankDto[]> {
    return this.httpClient.get<BankDto[]>(this.TARGET + this.BASE_API + "/crud/banks");
  }

  createCategory(name: string): Observable<Object> {
    const categoryCreationDto: CategoryCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/categories", categoryCreationDto);
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
    return this.httpClient.post<TransferCreationDto>(this.TARGET + this.BASE_API + "/crud/transfers/" + transferId, transferCreationDto);
  }

  editRule(ruleId: number, ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.TARGET + this.BASE_API + "/crud/rules/" + ruleId, ruleCreationDto);
  }

  getTransfers(): Observable<TransferDto[]> {
    return this.httpClient.get<TransferDto[]>(this.TARGET + this.BASE_API + "/crud/transfers");
  }

  createSpending(spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spendings", spendingCreationDto);
  }

  editSpending(id: number, spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spendings/" + id, spendingCreationDto);
  }

  confirmTransferChange(id: number) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/transfers/" + id + "/confirmchange", {});
  }

  confirmRuleChange(id: number) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/rules/" + id + "/confirmchange", {});
  }

  deleteTransfer(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/transfers/" + id);
  }

  deleteRule(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/rules/" + id);
  }

  deleteSpending(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/spendings/" + id);
  }

  getSpendingSum(): Observable<number> {
    return this.httpClient.get<number>(this.TARGET + this.BASE_API + "/overview/sum");
  }

  getDiffBetweenInAndOut(): Observable<number> {
    return this.httpClient.get<number>(this.TARGET + this.BASE_API + "/overview/diff");
  }
}
