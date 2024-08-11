import { Component } from '@angular/core';
import {Button} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";

@Component({
  selector: 'app-bank-creation',
  standalone: true,
    imports: [
        Button,
        CalendarModule,
        DropdownModule,
        FloatLabelModule,
        InputTextModule
    ],
  templateUrl: './bank-creation.component.html',
  styleUrl: './bank-creation.component.scss'
})
export class BankCreationComponent {

}
