import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";
import {BackendService} from "../../service/backend.service";
import {BankDto} from "../../model/backend";

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
  selectedFromBank = "";
  selectedTargetBank = "";

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {
    this.backendService.getBanks().subscribe(bankDtos => {
      this.banks = bankDtos;
    })

  }
}
