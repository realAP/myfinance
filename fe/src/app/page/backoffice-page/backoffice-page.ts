import { Component, ChangeDetectionStrategy, inject } from '@angular/core';
import {CardModule} from "primeng/card";
import {BankCreation} from "../../component/bank-creation/bank-creation";
import {CategoryCreation} from "../../component/category-creation/category-creation";

import {MessageService} from "primeng/api";
import {IncomeCreationDto, RuleCreationDto, SpendingCreationDto, TransferCreationDto} from "../../model/backend";
import {BackendService} from "../../service/backend/backend";
import {RuleForm} from "../../component/forms/rule-form/rule-form";
import {TransferForm} from "../../component/forms/transfer-form/transfer-form";
import {SpaceForm} from "../../component/forms/space-form/space-form";
import {SpendingForm} from "../../component/forms/spending-form/spending-form";
import {IncomeForm} from "../../component/forms/income-form/income-form";

@Component({
  selector: 'app-backoffice-page',
  standalone: true,
  imports: [
    BankCreation,
    CategoryCreation,
    RuleForm,
    TransferForm,
    SpaceForm,
    SpendingForm,
    CardModule,
    IncomeForm
  ],
  templateUrl: './backoffice-page.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './backoffice-page.scss'
})
export class BackofficePage {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);


  onTransferCreate(transferCreationDto: TransferCreationDto) {
    this.backendService.createTransfer(transferCreationDto).subscribe();
    this.messageService.add({
      severity: 'success',
      summary: 'created Transfer:',
      detail: transferCreationDto.description
    });
  }

  onRuleCreate(ruleCreationDto: RuleCreationDto) {
    this.backendService.createRule(ruleCreationDto).subscribe();
    this.messageService.add({
      severity: 'success',
      summary: 'created Rule:',
      detail: ruleCreationDto.description
    });
  }

  onSpendingCreate(spendingCreationDto: SpendingCreationDto) {
    this.backendService.createSpending(spendingCreationDto).subscribe();
    this.messageService.add({
      severity: 'success',
      summary: 'created Spending:',
      detail: spendingCreationDto.description
    });
  }

  onIncomeCreate(incomeCreationDto: IncomeCreationDto) {
    this.backendService.createIncome(incomeCreationDto).subscribe();
    this.messageService.add({
      severity: 'success',
      summary: 'created Income:',
      detail: incomeCreationDto.description
    });
  }
}
