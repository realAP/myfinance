import { Component, Input, OnInit, ChangeDetectionStrategy, inject, output } from '@angular/core';
import {InputGroupModule} from "primeng/inputgroup";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {FormsModule} from "@angular/forms";
import {Button} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {DatePickerModule} from "primeng/datepicker";
import {SelectModule} from "primeng/select";
import {InputNumberModule} from "primeng/inputnumber";
import {FloatLabelModule} from "primeng/floatlabel";
import {forkJoin} from "rxjs";
import {CategoryDto, RuleDto, SpendingCreationDto, TransferDto} from "../../../model/backend";
import {BackendService} from "../../../service/backend/backend.service";

export interface SpendingFormDto {
  spendingCreationDto: SpendingCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-spending-form',
  standalone: true,
  imports: [Button, FormsModule, InputGroupModule, InputGroupAddonModule, InputTextModule, DatePickerModule, SelectModule, InputNumberModule, FloatLabelModule],
  templateUrl: './spending-form.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './spending-form.component.scss'
})
export class SpendingFormComponent implements OnInit {
  private backendService = inject(BackendService);

  name: string = "";
  amount: number | undefined;
  categories: CategoryDto[] = [];
  selectedCategory: CategoryDto | undefined;
  transfers: TransferDto[] = [];
  selectedTransfer: TransferDto | undefined;
  rules: RuleDto[] = [];
  selectedRule: RuleDto | undefined;

  @Input() preFilledSpendingFormDto?: SpendingFormDto;
  readonly formSubmit = output<SpendingCreationDto>();

  ngOnInit(): void {

    forkJoin({
      categories: this.backendService.getCategories(),
      transfers: this.backendService.getTransfers(),
      rules: this.backendService.getRules()
    }).subscribe(({categories, transfers, rules}) => {
      this.categories = categories;
      this.transfers = transfers;
      this.rules = rules;

      if (this.preFilledSpendingFormDto?.isPreFilled) {
        const preFilledData = this.preFilledSpendingFormDto?.spendingCreationDto;
        this.selectedCategory = this.categories.find(category => category.id === preFilledData.categoryId)!;
        this.selectedTransfer = this.transfers.find(transfer => transfer.id === preFilledData.transferId)!;
        this.selectedRule = this.rules.find(rule => rule.id === preFilledData.ruleId)!;
        this.name = preFilledData.description;
        this.amount = preFilledData.amount;
      }
    })
  }

  onClick() {
    if (this.name && this.amount && this.selectedCategory && this.selectedTransfer && this.selectedRule) {
      const spendingCreationDto: SpendingCreationDto = {
        description: this.name,
        amount: this.amount,
        categoryId: this.selectedCategory.id,
        transferId: this.selectedTransfer.id,
        ruleId: this.selectedRule.id
      };
      this.formSubmit.emit(spendingCreationDto);
    }
    this.resetForm();
  }

  resetForm() {
    this.name = "";
    this.amount = undefined;
    this.selectedCategory = undefined;
    this.selectedTransfer = undefined;
    this.selectedRule = undefined;
  }

  onEvenizeSpending(id: number | undefined) {
    this.backendService.evenizeSpending(id).subscribe((next) => {
      this.amount = next;
    })
  }
}
