import {Component, OnInit} from '@angular/core';
import {InputGroupModule} from "primeng/inputgroup";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {FormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {InputNumberModule} from "primeng/inputnumber";
import {FloatLabelModule} from "primeng/floatlabel";

@Component({
  selector: 'app-spending-creation-page',
  standalone: true,
  imports: [FormsModule, InputGroupModule, InputGroupAddonModule, InputTextModule, CalendarModule, DropdownModule, InputNumberModule, FloatLabelModule],
  templateUrl: './spending-creation.component.html',
  styleUrl: './spending-creation.component.scss'
})
export class SpendingCreationComponent implements OnInit {
  date: any;
  categories: string[] = [];
  selectedCategory: string = "";

  transfers: string[] = [];
  selectedTransfer: string = "";

  rules: string[] = [];
  selectedRule: string = "";


  amount: number | null = null;

  ngOnInit(): void {
    this.categories = ["VERGNUEGEN", "SPORT", "BANK", "INVESTITIONEN"];
    this.transfers = ["Einzahlung", "Auszahlung"];
    this.rules = ["Regel 1", "Regel 2", "Regel 3"];
  }
}
