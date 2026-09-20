import { Injectable, inject } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {
  BankCreationDto,
  BankDto,
  CategoryCreationDto,
  CategoryDto,
  IncomeCreationDto,
  IncomeDto,
  RuleCreationDto,
  RuleDto,
  SpaceCreationDto,
  SpaceDto,
  SpendingCategoryBlockDto,
  SpendingCreationDto,
  TransferCreationDto,
  TransferDto
} from "../../model/backend";


@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private httpClient = inject(HttpClient);


  // Same-Origin: nginx reicht /fe/ an das Backend weiter, im
  // Entwicklungsbetrieb macht proxy.conf.json dasselbe.
  private BASE_API = "/fe"

  //
  // TODO: add error handling
  //

  getSpendingCategoryBlockDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.BASE_API + "/overview");
  }

  createSpace(name: string): Observable<Object> {
    const spaceCreationDto: SpaceCreationDto = {name: name};
    return this.httpClient.post(this.BASE_API + "/crud/spaces", spaceCreationDto);
  }

  getSpaces(): Observable<SpaceDto[]> {
    return this.httpClient.get<SpaceDto[]>(this.BASE_API + "/crud/spaces");
  }

  createBank(name: string): Observable<Object> {
    const bankCreationDto: BankCreationDto = {name: name};
    return this.httpClient.post(this.BASE_API + "/crud/banks", bankCreationDto);
  }

  getBanks(): Observable<BankDto[]> {
    return this.httpClient.get<BankDto[]>(this.BASE_API + "/crud/banks");
  }

  createCategory(name: string): Observable<Object> {
    const categoryCreationDto: CategoryCreationDto = {name: name};
    return this.httpClient.post(this.BASE_API + "/crud/categories", categoryCreationDto);
  }

  getCategories(): Observable<CategoryDto[]> {
    return this.httpClient.get<CategoryDto[]>(this.BASE_API + "/crud/categories");
  }

  createRule(ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.BASE_API + "/crud/rules", ruleCreationDto);
  }

  getRules(): Observable<RuleDto[]> {
    return this.httpClient.get<RuleDto[]>(this.BASE_API + "/crud/rules");
  }

  createTransfer(transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.BASE_API + "/crud/transfers", transferCreationDto);
  }

  editTransfer(transferId: number, transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.BASE_API + "/crud/transfers/" + transferId, transferCreationDto);
  }

  editRule(ruleId: number, ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.BASE_API + "/crud/rules/" + ruleId, ruleCreationDto);
  }

  getTransfers(): Observable<TransferDto[]> {
    return this.httpClient.get<TransferDto[]>(this.BASE_API + "/crud/transfers");
  }

  createSpending(spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.BASE_API + "/crud/spendings", spendingCreationDto);
  }

  editSpending(id: number, spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.BASE_API + "/crud/spendings/" + id, spendingCreationDto);
  }

  confirmTransferChange(id: number) {
    return this.httpClient.post(this.BASE_API + "/crud/transfers/" + id + "/confirmchange", {});
  }

  confirmRuleChange(id: number) {
    return this.httpClient.post(this.BASE_API + "/crud/rules/" + id + "/confirmchange", {});
  }

  deleteTransfer(id: number) {
    return this.httpClient.delete(this.BASE_API + "/crud/transfers/" + id);
  }

  deleteRule(id: number) {
    return this.httpClient.delete(this.BASE_API + "/crud/rules/" + id);
  }

  deleteSpending(id: number) {
    return this.httpClient.delete(this.BASE_API + "/crud/spendings/" + id);
  }

  getSpendingSum(): Observable<number> {
    return this.httpClient.get<number>(this.BASE_API + "/overview/sum");
  }

  getDiffBetweenInAndOut(): Observable<number> {
    return this.httpClient.get<number>(this.BASE_API + "/overview/diff");
  }

  createIncome(incomeCreationDto: IncomeCreationDto) {
    return this.httpClient.post(this.BASE_API + "/crud/incomes", incomeCreationDto);
  }

  getIncomes(): Observable<IncomeDto[]> {
    return this.httpClient.get<IncomeDto[]>(this.BASE_API + "/crud/incomes");
  }

  deleteIncome(id: number) {
    return this.httpClient.delete(this.BASE_API + "/crud/incomes/" + id);
  }

  editIncome(id: number, incomeCreationDto: IncomeCreationDto) {
    return this.httpClient.post(this.BASE_API + "/crud/incomes/" + id, incomeCreationDto);
  }

  getIncomeSum() {
    return this.httpClient.get<number>(this.BASE_API + "/features/sumofincome");
  }

  evenizeSpending(id: number | undefined): Observable<number> {
    return this.httpClient.get<number>(this.BASE_API + "/features/evenize/" + id);
  }
}
