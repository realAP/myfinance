import {Component, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputGroupAddonModule} from "primeng/inputgroupaddon";
import {InputGroupModule} from "primeng/inputgroup";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-rule-creation',
  standalone: true,
  imports: [
    Button,
    DropdownModule,
    FloatLabelModule,
    InputGroupAddonModule,
    InputGroupModule,
    InputNumberModule,
    InputTextModule,
    CalendarModule,
    FormsModule
  ],
  templateUrl: './rule-creation.component.html',
  styleUrl: './rule-creation.component.scss'
})
export class RuleCreationComponent implements OnInit {
  date: any;
  spaces: string[] = [];
  selectedFromSpace= "";
  selectedTargetSpace="";

  ngOnInit(): void {
    this.spaces = ["main", "Haushalt", "buffer","Dauerspaß","Urlaub","Apple Watch","KFZ Fix","Einnahmen","Versicherung","iPhone","do not touch"]
  }

}
