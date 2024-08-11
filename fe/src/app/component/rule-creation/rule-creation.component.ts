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
import {BackendService} from "../../service/backend.service";
import {SpaceDto} from "../../model/backend";

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
  spaces: SpaceDto[] = [];
  selectedFromSpace: SpaceDto = {} as SpaceDto;
  selectedTargetSpace: SpaceDto = {} as SpaceDto;

  constructor(private backendService: BackendService) {
  }

  ngOnInit(): void {

    this.backendService.getSpaces().subscribe(data => {
      this.spaces = data;
    })
  }

  onClick() {
    console.log("From Space: " + this.selectedFromSpace.id);
    console.log("Target Space: " + this.selectedTargetSpace.id);
    console.log("Date: " + this.date);

  }
}
