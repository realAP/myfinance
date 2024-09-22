import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
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

  // Error handling function
  private handleError(error: HttpErrorResponse): Observable<never> {
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error
      console.error('An error occurred:', error.error.message);
    } else {
      // Backend error
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // Throw an observable with a user-facing error message
    return throwError(() => new Error('Something went wrong. Please try again later.'));
  }

  getSpendingCategoryBlockDto(): Observable<SpendingCategoryBlockDto[]> {
    return this.httpClient.get<SpendingCategoryBlockDto[]>(this.TARGET + this.BASE_API + "/overview")
      .pipe(catchError(this.handleError));

  }

  createSpace(name: string): Observable<Object> {
    const spaceCreationDto: SpaceCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spaces", spaceCreationDto)
      .pipe(catchError(this.handleError))
  }

  getSpaces(): Observable<SpaceDto[]> {
    return this.httpClient.get<SpaceDto[]>(this.TARGET + this.BASE_API + "/crud/spaces")
      .pipe(catchError(this.handleError));
  }

  createBank(name: string): Observable<Object> {
    const bankCreationDto: BankCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/banks", bankCreationDto).pipe(catchError(this.handleError));
  }

  getBanks(): Observable<BankDto[]> {
    return this.httpClient.get<BankDto[]>(this.TARGET + this.BASE_API + "/crud/banks").pipe(catchError(this.handleError));
  }

  createCategory(name: string) {
    const categoryCreationDto: CategoryCreationDto = {name: name};
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/categories", categoryCreationDto).pipe(catchError(this.handleError));
  }

  getCategories(): Observable<CategoryDto[]> {
    return this.httpClient.get<CategoryDto[]>(this.TARGET + this.BASE_API + "/crud/categories").pipe(catchError(this.handleError));
  }

  createRule(ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.TARGET + this.BASE_API + "/crud/rules", ruleCreationDto).pipe(catchError(this.handleError));
  }

  getRules(): Observable<RuleDto[]> {
    return this.httpClient.get<RuleDto[]>(this.TARGET + this.BASE_API + "/crud/rules").pipe(catchError(this.handleError));
  }

  createTransfer(transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.TARGET + this.BASE_API + "/crud/transfers", transferCreationDto).pipe(catchError(this.handleError));
  }

  editTransfer(transferId: number, transferCreationDto: TransferCreationDto): Observable<TransferCreationDto> {
    return this.httpClient.post<TransferCreationDto>(this.TARGET + this.BASE_API + "/crud/transfers/" + transferId, transferCreationDto).pipe(catchError(this.handleError));
  }

  editRule(ruleId: number, ruleCreationDto: RuleCreationDto): Observable<RuleCreationDto> {
    return this.httpClient.post<RuleCreationDto>(this.TARGET + this.BASE_API + "/crud/rules/" + ruleId, ruleCreationDto).pipe(catchError(this.handleError));
  }

  getTransfers(): Observable<TransferDto[]> {
    return this.httpClient.get<TransferDto[]>(this.TARGET + this.BASE_API + "/crud/transfers").pipe(catchError(this.handleError));
  }

  createSpending(spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spendings", spendingCreationDto).pipe(catchError(this.handleError));
  }

  editSpending(id: number, spendingCreationDto: SpendingCreationDto) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/spendings/" + id, spendingCreationDto).pipe(catchError(this.handleError));
  }

  confirmTransferChange(id: number) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/transfers/" + id + "/confirmchange", {}).pipe(catchError(this.handleError));
  }

  confirmRuleChange(id: number) {
    return this.httpClient.post(this.TARGET + this.BASE_API + "/crud/rules/" + id + "/confirmchange", {}).pipe(catchError(this.handleError));
  }

  deleteTransfer(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/transfers/" + id).pipe(catchError(this.handleError));
  }

  deleteRule(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/rules/" + id).pipe(catchError(this.handleError));
  }

  deleteSpending(id: number) {
    return this.httpClient.delete(this.TARGET + this.BASE_API + "/crud/spendings/" + id).pipe(catchError(this.handleError));
  }

  getSpendingSum(): Observable<number> {
    return this.httpClient.get<number>(this.TARGET + this.BASE_API + "/overview/sum").pipe(catchError(this.handleError));
  }
}
