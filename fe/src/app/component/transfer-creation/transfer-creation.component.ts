import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {FormsModule} from "@angular/forms";

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
  banks: string[] = [];
  selectedFromBank = "";
  selectedTargetBank = "";

  ngOnInit(): void {
    this.banks = ['N26', 'Volksbank', 'ING'];
  }
}
