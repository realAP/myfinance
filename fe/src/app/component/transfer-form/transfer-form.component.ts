import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BankDto, TransferCreationDto} from "../../model/backend";
import {MessageService} from "primeng/api";
import {BackendService} from "../../service/backend/backend.service";

export interface TransferFormDto {
  transferCreationDto: TransferCreationDto;
  isPreFilled: boolean;
}

@Component({
  selector: 'app-transfer-form',
  standalone: true,
  imports: [
    Button,
    CalendarModule,
    DropdownModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './transfer-form.component.html',
  styleUrl: './transfer-form.component.scss'
})
export class TransferFormComponent implements OnInit {
  date: any;
  banks: BankDto[] = [];
  selectedFromBank: BankDto = {} as BankDto;
  selectedTargetBank: BankDto = {} as BankDto;
  name: string = "";

  @Input() preFilledTransferFormDto?: TransferFormDto;
  @Output() formSubmit = new EventEmitter<TransferCreationDto>

  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.backendService.getBanks().subscribe(bankDtos => {
      this.banks = bankDtos;
      if (this.preFilledTransferFormDto?.isPreFilled) {
        const preFilledData = this.preFilledTransferFormDto?.transferCreationDto;
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
