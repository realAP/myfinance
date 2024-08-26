import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {BackendService} from "../../service/backend.service";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-bank-creation',
  standalone: true,
  imports: [
    Button,
    CalendarModule,
    DropdownModule,
    FloatLabelModule,
    InputTextModule,
    FormsModule
  ],
  templateUrl: './bank-creation.component.html',
  styleUrl: './bank-creation.component.scss'
})
export class BankCreationComponent {

  name: string = "";

  constructor(private backendService: BackendService) {
  }

  onCreateBank() {
    this.backendService.createBank(this.name);
    this.name = "";
  }

}
