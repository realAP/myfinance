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
  selectedRule: string = "empty";


  amount: number | null = null;

  ngOnInit(): void {
    this.categories = ["Vergnuegen", "Sport", "Bank", "Investitionen"];
    this.transfers = ["Spotify","Netflix","Amazon Prime","Disney+"];
    this.rules = ["Einnahmen -> main (24)", "Einnahmen -> main (01)", ""];
  }
}
