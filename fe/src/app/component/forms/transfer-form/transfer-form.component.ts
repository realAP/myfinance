import { Component, OnInit, ChangeDetectionStrategy, inject, input, output } from '@angular/core';
import {Button} from "primeng/button";
import {DatePickerModule} from "primeng/datepicker";
import {SelectModule} from "primeng/select";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {MessageService} from "primeng/api";
import {BankDto, TransferCreationDto} from "../../../model/backend";
import {BackendService} from "../../../service/backend/backend.service";

export interface TransferFormDto {
  transferCreationDto: TransferCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-transfer-form',
  standalone: true,
  imports: [
    Button,
    DatePickerModule,
    SelectModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './transfer-form.component.html',
  changeDetection: ChangeDetectionStrategy.Eager,
  styleUrl: './transfer-form.component.scss'
})
export class TransferFormComponent implements OnInit {
  private backendService = inject(BackendService);
  private messageService = inject(MessageService);

  date: any;
  banks: BankDto[] = [];
  selectedFromBank: BankDto = {} as BankDto;
  selectedTargetBank: BankDto = {} as BankDto;
  name: string = "";

  readonly preFilledTransferFormDto = input<TransferFormDto>();
  readonly formSubmit = output<TransferCreationDto>();

  ngOnInit(): void {
    this.backendService.getBanks().subscribe(bankDtos => {
      this.banks = bankDtos;
      const preFilledTransferFormDto = this.preFilledTransferFormDto();
      if (preFilledTransferFormDto?.isPreFilled) {
        const preFilledData = preFilledTransferFormDto?.transferCreationDto;
        this.selectedFromBank = this.banks.find(bank => bank.id === preFilledData.fromBankId)!;
        this.selectedTargetBank = this.banks.find(bank => bank.id === preFilledData.toBankId)!;
        this.date = new Date(preFilledData.dateOfExecution);
        this.name = preFilledData.description
      }
    });
  }

  onClick() {
    if (!this.selectedFromBank.id || !this.selectedTargetBank.id || !this.date || !this.name) {
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please fill in all fields'});
      return;
    }
    const transferCreationDto: TransferCreationDto = {
      description: this.name,
      dateOfExecution: this.date.toLocaleDateString("en-CA"),
      fromBankId: this.selectedFromBank.id,
      toBankId: this.selectedTargetBank.id,
    }
    this.formSubmit.emit(transferCreationDto);
  }
}
