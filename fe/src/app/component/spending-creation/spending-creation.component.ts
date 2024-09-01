import {Component, OnInit} from '@angular/core';
import {InputGroupModule} from "primeng/inputgroup";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {InputNumberModule} from "primeng/inputnumber";
import {FloatLabelModule} from "primeng/floatlabel";
import {BackendService} from "../../service/backend.service";
import {CategoryDto, RuleDto, SpendingCreationDto, TransferDto} from "../../model/backend";

@Component({
  selector: 'app-spending-creation-page',
  standalone: true,
  imports: [FormsModule, InputGroupModule, InputGroupAddonModule, InputTextModule, CalendarModule, DropdownModule, InputNumberModule, FloatLabelModule],
  templateUrl: './spending-creation.component.html',
  styleUrl: './spending-creation.component.scss'
})
export class SpendingCreationComponent implements OnInit {
  name: string = "";
  amount: number | undefined;

  categories: CategoryDto[] = [];
  selectedCategory: CategoryDto | undefined;

  transfers: TransferDto[] = [];
  selectedTransfer: TransferDto | undefined;

  rules: RuleDto[] = [];
  selectedRule: RuleDto | undefined;

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {
    this.backendService.getCategories().subscribe((categories) => {
      this.categories = categories
    });
    this.backendService.getTransfers().subscribe((transfers) => {
      this.transfers = transfers
    })
    this.backendService.getRules().subscribe((rules) => {
      this.rules = rules
    })
  }

  onCreateSpending() {
    if (this.name && this.amount && this.selectedCategory && this.selectedTransfer && this.selectedRule) {
      const spendingCreationDto: SpendingCreationDto = {
        description: this.name,
        amount: this.amount,
        categoryId: this.selectedCategory.id,
        transferId: this.selectedTransfer.id,
        ruleId: this.selectedRule.id
      };
      this.backendService.createSpending(spendingCreationDto);
    } else {
      console.error("All fields are required.");
    }

  }
}
