import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {BankDto, TransferCreationDto} from "../../model/backend";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-transfer-creation',
  standalone: true,
  imports: [
    Button,
    CalendarModule,
    DropdownModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './transfer-creation.component.html',
  styleUrl: './transfer-creation.component.scss'
})
export class TransferCreationComponent implements OnInit {
  date: any;
  banks: BankDto[] = [];
  selectedFromBank: BankDto = {} as BankDto;
  selectedTargetBank: BankDto = {} as BankDto;
  name: string = "";

  constructor(private backendService: BackendService,
              private messageService: MessageService) {
  }

  ngOnInit(): void {
    this.backendService.getBanks().subscribe(bankDtos => {
      this.banks = bankDtos;
    })

  }

  onClick() {
    if (!this.selectedFromBank.id || !this.selectedTargetBank.id || !this.date || !this.name) {
      this.messageService.add({severity: 'error', summary: 'Error', detail: 'Please fill in all fields'});
      return;
    }

    const transferCreationDto: TransferCreationDto = {
      description: this.name,
      dateOfExecution: this.date.toISOString(),
      fromBankId: this.selectedFromBank.id,
      toBankId: this.selectedTargetBank.id,
    }

    this.backendService.createTransfer(transferCreationDto).subscribe();
    this.messageService.add({severity: 'success', summary: 'created Transfer:', detail: this.name});
  }
}
