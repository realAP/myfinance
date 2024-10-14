import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
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
    CalendarModule,
    DropdownModule,
    FloatLabelModule,
    InputTextModule,
    ReactiveFormsModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    FormsModule
  ],
  templateUrl: './income-form.component.html',
  styleUrl: './income-form.component.scss'
})
export class IncomeFormComponent implements OnInit {
  description: string = "";
  amount: number | undefined;


  @Input() preFilledIncomeFormDto?: IncomeFormDto;
  @Output() formSubmit = new EventEmitter<IncomeCreationDto>;

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {
    if (this.preFilledIncomeFormDto?.isPreFilled) {
      this.description = this.preFilledIncomeFormDto.incomeCreationDto.description;
      this.amount = this.preFilledIncomeFormDto.incomeCreationDto.amount;
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
