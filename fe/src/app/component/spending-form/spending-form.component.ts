import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {InputGroupModule} from "primeng/inputgroup";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {InputNumberModule} from "primeng/inputnumber";
import {FloatLabelModule} from "primeng/floatlabel";
import {CategoryDto, RuleDto, SpendingCreationDto, TransferDto} from "../../model/backend";
import {MessageService} from "primeng/api";
import {forkJoin} from "rxjs";
import {BackendService} from "../../service/backend/backend.service";

export interface SpendingFormDto {
  spendingCreationDto: SpendingCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-spending-form',
  standalone: true,
  imports: [FormsModule, InputGroupModule, InputGroupAddonModule, InputTextModule, CalendarModule, DropdownModule, InputNumberModule, FloatLabelModule],
  templateUrl: './spending-form.component.html',
  styleUrl: './spending-form.component.scss'
})
export class SpendingFormComponent implements OnInit {
  name: string = "";
  amount: number | undefined;
  categories: CategoryDto[] = [];
  selectedCategory: CategoryDto | undefined;
  transfers: TransferDto[] = [];
  selectedTransfer: TransferDto | undefined;
  rules: RuleDto[] = [];
  selectedRule: RuleDto | undefined;

  @Input() preFilledSpendingFormDto?: SpendingFormDto;
  @Output() formSubmit = new EventEmitter<SpendingCreationDto>;

  constructor(private backendService: BackendService) {
  }

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
    this.amount = 0;
    this.selectedCategory = undefined;
    this.selectedTransfer = undefined;
    this.selectedRule = undefined;
  }
}
