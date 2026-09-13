import { Component, OnInit, ChangeDetectionStrategy, inject, input, output } from '@angular/core';
import {Button} from "primeng/button";
import {DatePickerModule} from "primeng/datepicker";
import {SelectModule} from "primeng/select";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {InputGroupModule} from "primeng/inputgroup";
import {InputNumberModule} from "primeng/inputnumber";
import {BackendService} from "../../../service/backend/backend.service";
import {IncomeCreationDto} from "../../../model/backend";

export interface IncomeFormDto {
  incomeCreationDto: IncomeCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-income-form',
  standalone: true,
  imports: [
    Button,
    DatePickerModule,
    SelectModule,
    FloatLabelModule,
    InputTextModule,
    ReactiveFormsModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    FormsModule
  ],
  templateUrl: './income-form.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './income-form.component.scss'
})
export class IncomeFormComponent implements OnInit {
  private backendService = inject(BackendService);

  description: string = "";
  amount: number | undefined;


  readonly preFilledIncomeFormDto = input<IncomeFormDto>();
  readonly formSubmit = output<IncomeCreationDto>();

  ngOnInit(): void {
    const preFilledIncomeFormDto = this.preFilledIncomeFormDto();
    if (preFilledIncomeFormDto?.isPreFilled) {
      this.description = preFilledIncomeFormDto.incomeCreationDto.description;
      this.amount = preFilledIncomeFormDto.incomeCreationDto.amount;
    }
  }

  onClick() {
    if (this.description && this.amount) {
      const incomeCreationDto: IncomeCreationDto = {
        description: this.description,
        amount: this.amount
      };
      this.formSubmit.emit(incomeCreationDto);
    }
    this.resetForm();
  }

  resetForm() {
    this.description = "";
    this.amount = undefined;
  }
}
